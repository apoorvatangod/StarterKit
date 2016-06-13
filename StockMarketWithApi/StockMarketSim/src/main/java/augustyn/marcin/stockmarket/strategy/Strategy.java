package augustyn.marcin.stockmarket.strategy;

import java.util.List;

import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

public interface Strategy extends OfferAnalyzer{
	public List<StockActionToPerform> getActions(List<PlayerFoundTo> founds, List<PlayerShareTo> shares);
}
