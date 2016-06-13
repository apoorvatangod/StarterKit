package augustyn.marcin.stockmarket.player;

import java.util.List;

import augustyn.marcin.stockmarket.broker.to.OfferTo;

public interface Player {
	
	public List<OfferTo> performActions();
}
