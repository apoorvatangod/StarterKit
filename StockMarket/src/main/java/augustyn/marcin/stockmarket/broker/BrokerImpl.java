package augustyn.marcin.stockmarket.broker;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.bank.Bank;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.broker.entity.PlayerShareEntity;
import augustyn.marcin.stockmarket.broker.exception.BrokerActionNoValid;
import augustyn.marcin.stockmarket.broker.exception.InsufficientShareBalance;
import augustyn.marcin.stockmarket.broker.mapper.OfferMapper;
import augustyn.marcin.stockmarket.broker.mapper.PlayerShareMapper;
import augustyn.marcin.stockmarket.broker.repository.OfferRepository;
import augustyn.marcin.stockmarket.broker.repository.PlayerShareRepository;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.calendar.MyCalendar;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;
import augustyn.marcin.stockmarket.enumation.TransactionType;
import augustyn.marcin.stockmarket.main.Randomizer;
import augustyn.marcin.stockmarket.stock.Stock;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class BrokerImpl implements Broker {
	private static final Logger logger = LogManager.getLogger(BrokerImpl.class);	
	private static final int MINIMAL_COMISSION_FEE = 500;
	private static final float COMISSION_FEE_PERCENTAGE = 0.005f;
	private static final float MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE = 0.8f;
	private static final float MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE = 0.98f;
	private static final float MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE = 1.02f;
	private static final float ONE_HUNDRED_PERCENT = 1.0f;
	
	@Autowired
	private Stock stock;
	
	@Autowired
	private Bank bank;
	
	@Autowired
	private PlayerShareRepository playerShareRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private MyCalendar calendar;
	//TODO weryfikacja przy executeOffer czy przeslana oferta zgadza sie z wystawiona i po zakonczeniu execte musi byc aktualizacja statusu oferty na COMPLETED, statyczny helper nowa klasa
	@Override
	public OfferTo getSellOffer(String share, int quantity) {
		try {
			validateOfferRequest(share,quantity);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		int offeredQuantity = randomShareToBuySellQuantity(quantity);
		int offeredPrice = randomShareSellPrice(stock.getCurrentDataForShare(share).getPrice());
		int comission = calculateComission(offeredQuantity, offeredPrice);
		
		OfferTo offer = new OfferTo(null, ActionType.SELL, share, offeredQuantity, offeredPrice, 
				calendar.getCurrentDate().toDate(), Currency.PLN, comission, OfferStatus.ACTIVE);
		
		offerRepository.save(OfferMapper.map(offer));
		return offer;
	}

	@Override
	public OfferTo getBuyOffer(String share, int quantity) {
		try {
			validateOfferRequest(share,quantity);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		
		int offeredQuantity = randomShareToBuySellQuantity(quantity);
		int offeredPrice = randomShareBuyPrice(stock.getCurrentDataForShare(share).getPrice());
		int comission = calculateComission(offeredQuantity, offeredPrice);
		
		OfferTo offer = new OfferTo(null, ActionType.BUY, share, offeredQuantity, offeredPrice, 
				calendar.getCurrentDate().toDate(), Currency.PLN, comission, OfferStatus.ACTIVE);
		
		offerRepository.save(OfferMapper.map(offer));
		return offer;
	}

	@Override
	public List<PlayerShareTo> checkShareBalance() {
		return PlayerShareMapper.map2To(playerShareRepository.findAll());
	}

	@Override
	public FoundTransactionTo executeSellOffer(OfferTo offer) {
		try {
			confirmSufficientShareBalance(offer);
			validateSellRequest(offer);
		} catch (InsufficientShareBalance | BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}

		PlayerShareEntity share = playerShareRepository.findPlayerShareByName(offer.getShare()).get(0);
		share.setQuantity(share.getQuantity() - offer.getQuantity());
		playerShareRepository.save(share);
		int totalCost = (offer.getPrice() * offer.getQuantity()) - offer.getComission();
		return bank.executeTransaction(TransactionType.DEPOSIT, offer.getCurrency(), totalCost);
	}

	@Override
	public PlayerShareTo executeBuyOffer(OfferTo offer, FoundTransactionTo transactionConfirmation) {
		try {
			validateBuyRequest(offer);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		if(bank.confirmTransaction(transactionConfirmation)){
			PlayerShareEntity share = playerShareRepository.findPlayerShareByName(offer.getShare()).get(0);
			share.setQuantity(share.getQuantity() + offer.getQuantity());
			return PlayerShareMapper.map(playerShareRepository.save(share));
		}
		return null;
	}

	@Override
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays) {
		return stock.getHistoryDataForShare(share, historySizeInDays);
	}
	
	private int randomShareToBuySellQuantity(int requestedQuantity){
		int offeredQuantity = 0;
		do{
			Float offeredQuantityPercentage = Randomizer.randomFloat(MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE, ONE_HUNDRED_PERCENT);
			offeredQuantity = (int) Math.round(requestedQuantity *  offeredQuantityPercentage);
		}while(offeredQuantity > requestedQuantity || offeredQuantity < (requestedQuantity * MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE));
		
		return offeredQuantity;
	}
	
	private int randomShareSellPrice(int stockPrice){
		int offeredPrice = 0;
		do{
			Float offeredPricePercentage = Randomizer.randomFloat(MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE, ONE_HUNDRED_PERCENT);
			offeredPrice = (int) Math.round(stockPrice * offeredPricePercentage);
		}while(offeredPrice > stockPrice || offeredPrice < (stockPrice * MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE));
		
		return offeredPrice;
	}
	
	private int randomShareBuyPrice(int stockPrice){
		int offeredPrice = 0;
		do{
			Float offeredPricePercentage = Randomizer.randomFloat(ONE_HUNDRED_PERCENT, MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE);

			offeredPrice = (int) Math.round(stockPrice * offeredPricePercentage);
		}while(offeredPrice < stockPrice || offeredPrice > (stockPrice * MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE));
		
		return offeredPrice;
	}
	
	private int calculateComission(int offeredQuantity, int offeredPrice){

		if(Math.round(offeredQuantity * offeredPrice * COMISSION_FEE_PERCENTAGE) > MINIMAL_COMISSION_FEE){
			return (int) Math.round(offeredQuantity * offeredPrice * COMISSION_FEE_PERCENTAGE);
		}
		return MINIMAL_COMISSION_FEE;
	}
	
	private void confirmSufficientShareBalance(OfferTo offer) throws InsufficientShareBalance{
		if(playerShareRepository.findPlayerShareByName(offer.getShare()).get(0).getQuantity() < offer.getQuantity()){
			throw new InsufficientShareBalance("Player doesnt have enough shares to sell");
		}
	}
	
	private void validateBuyRequest(OfferTo offer) throws BrokerActionNoValid{
		if(offer.getActionType() != ActionType.BUY || offer.getStatus() != OfferStatus.ACTIVE){
			throw new BrokerActionNoValid("Buy request not valid - wrong action type or offer not active");
		}
	}
	
	private void validateSellRequest(OfferTo offer) throws BrokerActionNoValid{
		if(offer.getActionType() != ActionType.SELL || offer.getStatus() != OfferStatus.ACTIVE){
			throw new BrokerActionNoValid("Sell request not valid - wrong action type or offer not active");
		}
	}
	
	private void validateOfferRequest(String share, int quantity) throws BrokerActionNoValid{
		if(share == null || quantity <= 0){
			throw new BrokerActionNoValid("Offer request not valid - share is null or quantity is negative");
		}
	}
}
