package augustyn.marcin.stockmarket.main;

import java.util.List;

import augustyn.marcin.stockmarket.broker.to.OfferTo;

public interface Main {
	public List<OfferTo> executeSim();
}
