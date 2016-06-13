package augustyn.marcin.stockmarket.player;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.bank.Bank;
import augustyn.marcin.stockmarket.bank.exception.InsufficientFoundBalance;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.broker.Broker;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;
import augustyn.marcin.stockmarket.strategy.Strategy;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

@Service
public class PlayerImpl implements Player {
	private static final Logger logger = LogManager.getLogger(PlayerImpl.class);
	
	@Autowired
	private Bank bank;
	
	@Autowired
	private Broker broker;
	
	@Autowired
	private Strategy strategy;

	@Override
	public List<OfferTo> performActions() {
		List<StockActionToPerform> actions = strategy.getActions(checkBankResources(), checkBrokerResources());
		List<OfferTo> executedOffers = new ArrayList<>();
		for(StockActionToPerform action : actions){
			if(action.getType() == ActionType.BUY){
				OfferTo executedOffer = performBuyAction(action);
				if(executedOffer != null){
					logger.info("Day: " + executedOffer.getDate() + " BOUGHT: " + executedOffer.getShare() + ", in quantity: " + 
							executedOffer.getQuantity() + " for " + executedOffer.getPrice() + " " + executedOffer.getCurrency());
					executedOffers.add(executedOffer);
				}
			}
			if(action.getType() == ActionType.SELL){
				OfferTo executedOffer = performSellAction(action);
				if(executedOffer != null){
					logger.info("Day: " + executedOffer.getDate() + " - SOLD " + executedOffer.getQuantity() + " " + executedOffer.getShare() + 
							" actions FOR: " + executedOffer.getPrice() + " " + executedOffer.getCurrency());
					executedOffers.add(executedOffer);
				}
			}
		}
		return executedOffers;
	}
	
	private OfferTo performBuyAction(StockActionToPerform action){
		OfferTo offer = broker.getBuyOffer(action.getShareName(), action.getQuantity());
		if(strategy.analyzeBuyOffer(offer, action)){
			Integer totalTransactionPrice = offer.getPrice() * offer.getQuantity() + offer.getComission();
			FoundTransactionTo confirmation = bank.executeTransaction(TransactionType.WITHDRAW, offer.getCurrency(), totalTransactionPrice);
			return broker.executeBuyOffer(offer, confirmation);
		}
		return null;
	}
	
	private OfferTo performSellAction(StockActionToPerform action){
		OfferTo offer = broker.getSellOffer(action.getShareName(), action.getQuantity());
		return strategy.analyzeSellOffer(offer, action) ? broker.executeSellOffer(offer) : null;
	}
	
	@SuppressWarnings("unused")
	private void performExchange(Currency input, Currency output, int quantity){
		try {
			bank.executeExchange(input, output, quantity);
		} catch (InsufficientFoundBalance e) {
			logger.info(e.getMessage());
		}
	}
	
	private List<PlayerFoundTo> checkBankResources(){
		return bank.checkBalance();
	}
	
	private List<PlayerShareTo> checkBrokerResources(){
		return broker.checkShareBalance();
	}
}
