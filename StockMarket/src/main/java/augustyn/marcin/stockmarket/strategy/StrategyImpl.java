package augustyn.marcin.stockmarket.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.broker.Broker;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

@Service
public class StrategyImpl implements Strategy {
	
	@Autowired
	private Broker broker;

	@Override
	public boolean analyzeSellOffer(OfferTo offer, StockActionToPerform action) {
		return offer.getPrice() >= action.getMinPrice();
	}

	@Override
	public boolean analyzeBuyOffer(OfferTo offer, StockActionToPerform action) {
		return offer.getPrice() <= action.getMaxPrice();
	}

	@Override
	public List<StockActionToPerform> getActions(List<PlayerFoundTo> founds, List<PlayerShareTo> shares) {
		List<ShareDataTo> shareData = getRequiredShareData();
		
		if(!shareData.isEmpty() && shareData.get(0).getPrice() <= 1000 && getFoundQuantityByCurrency(founds, Currency.PLN) > 900000){
			return Arrays.asList(new StockActionToPerform(ActionType.BUY, "PKO", 800, null, 1200, Currency.PLN, shareData.get(0).getDate()));
		}
		
		if(!shareData.isEmpty() && shareData.get(0).getPrice() >= 1500 && getOwnedShareQuantityByShareName(shares, "PKO") > 0){
			return Arrays.asList(new StockActionToPerform(ActionType.SELL, "PKO", getOwnedShareQuantityByShareName(shares, "PKO"), 
					1400, null, Currency.PLN, shareData.get(0).getDate()));
		}
		return new ArrayList<StockActionToPerform>();
	}
	
	private List<ShareDataTo> getRequiredShareData(){
		return Arrays.asList(broker.getCurrentDataForShare("PKO"));
	}
	
	private int getFoundQuantityByCurrency(List<PlayerFoundTo> founds, Currency currency){
		for(PlayerFoundTo found : founds){
			if(found.getCurrency() == currency){
				return found.getQuantity();
			}
		}
		return 0;
	}
	
	private int getOwnedShareQuantityByShareName(List<PlayerShareTo> shares, String shareName){
		for(PlayerShareTo share : shares){
			if(share.getName().equals(shareName)){
				return share.getQuantity();
			}
		}
		return 0;
	}
}
