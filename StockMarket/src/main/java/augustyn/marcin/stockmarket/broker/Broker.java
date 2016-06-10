package augustyn.marcin.stockmarket.broker;

import java.util.List;

import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

public interface Broker {
	
	public OfferTo getSellOffer(String share, int quantity);
	
	public OfferTo getBuyOffer(String share, int quantity);
	
	public List<PlayerShareTo> checkShareBalance();
	
	public OfferTo executeSellOffer(OfferTo offer);
	
	public OfferTo executeBuyOffer(OfferTo offer, FoundTransactionTo transactionConfirmation);
	
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays);
	
	public ShareDataTo getCurrentDataForShare(String share);
}
