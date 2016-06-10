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
import augustyn.marcin.stockmarket.stock.Stock;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class BrokerImpl implements Broker {
	private static final Logger logger = LogManager.getLogger(BrokerImpl.class);	
	
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
	
	@Override
	public OfferTo getSellOffer(String share, int quantity) {
		try {
			BrokerHelper.validateOfferRequest(share,quantity);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		int offeredQuantity = BrokerHelper.randomShareToBuySellQuantity(quantity);
		int offeredPrice = BrokerHelper.randomShareSellPrice(stock.getCurrentDataForShare(share).getPrice());
		int comission = BrokerHelper.calculateComission(offeredQuantity, offeredPrice);
		
		OfferTo offer = new OfferTo(null, ActionType.SELL, share, offeredQuantity, offeredPrice, 
				calendar.getCurrentDate().toDate(), Currency.PLN, comission, OfferStatus.ACTIVE);
		
		offerRepository.save(OfferMapper.map(offer));
		return offer;
	}

	@Override
	public OfferTo getBuyOffer(String share, int quantity) {
		try {
			BrokerHelper.validateOfferRequest(share,quantity);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		
		int offeredQuantity = BrokerHelper.randomShareToBuySellQuantity(quantity);
		int offeredPrice = BrokerHelper.randomShareBuyPrice(stock.getCurrentDataForShare(share).getPrice());
		int comission = BrokerHelper.calculateComission(offeredQuantity, offeredPrice);
		
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
	public OfferTo executeSellOffer(OfferTo offer) {
		try {
			confirmSufficientShareBalance(offer);
			BrokerHelper.validateSellRequest(offer);
		} catch (InsufficientShareBalance | BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}

		PlayerShareEntity share = playerShareRepository.findPlayerShareByName(offer.getShare());
		share.setQuantity(share.getQuantity() - offer.getQuantity());
		playerShareRepository.save(share);
		
		int totalCost = (offer.getPrice() * offer.getQuantity()) - offer.getComission();
		if(bank.executeTransaction(TransactionType.DEPOSIT, offer.getCurrency(), totalCost) == null){
			return null;
		}
		
		offer.setStatus(OfferStatus.COMPLETED);
		offerRepository.save(OfferMapper.map(offer));
		return offer;
	}

	@Override
	public OfferTo executeBuyOffer(OfferTo offer, FoundTransactionTo transactionConfirmation) {
		try {
			BrokerHelper.validateBuyRequest(offer);
		} catch (BrokerActionNoValid e) {
			logger.info(e.getMessage());
			return null;
		}
		if(bank.confirmTransaction(transactionConfirmation)){
			PlayerShareEntity share = playerShareRepository.findPlayerShareByName(offer.getShare());
			share.setQuantity(share.getQuantity() + offer.getQuantity());
			playerShareRepository.save(share);
			
			offer.setStatus(OfferStatus.COMPLETED);
			offerRepository.save(OfferMapper.map(offer));
			return offer;
		}
		return null;
	}

	@Override
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays) {
		return stock.getHistoryDataForShare(share, historySizeInDays);
	}

	@Override
	public ShareDataTo getCurrentDataForShare(String share) {
		return stock.getCurrentDataForShare(share);
	}
	
	private void confirmSufficientShareBalance(OfferTo offer) throws InsufficientShareBalance{
		if(playerShareRepository.findPlayerShareByName(offer.getShare()).getQuantity() < offer.getQuantity()){
			throw new InsufficientShareBalance("Player doesnt have enough shares to sell");
		}
	}
}
