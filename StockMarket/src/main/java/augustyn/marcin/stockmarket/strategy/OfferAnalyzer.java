package augustyn.marcin.stockmarket.strategy;

import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

public interface OfferAnalyzer {
	
	public boolean analyzeSellOffer(OfferTo offer, StockActionToPerform action);
	
	public boolean analyzeBuyOffer(OfferTo offer, StockActionToPerform action);
}
