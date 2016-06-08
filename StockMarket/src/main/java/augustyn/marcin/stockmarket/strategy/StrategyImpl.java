package augustyn.marcin.stockmarket.strategy;

import java.util.List;

import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

@Service
public class StrategyImpl implements Strategy {

	@Override
	public boolean analyzeSellOffer(OfferTo offer, StockActionToPerform action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean analyzeBuyOffer(OfferTo offer, StockActionToPerform action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StockActionToPerform> getActions(List<PlayerFoundTo> founds, List<PlayerShareTo> shares) {
		// TODO Auto-generated method stub
		return null;
	}

}
