package augustyn.marcin.stockmarket.broker;

import augustyn.marcin.stockmarket.broker.exception.BrokerActionNoValid;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.OfferStatus;
import augustyn.marcin.stockmarket.main.Randomizer;

public class BrokerHelper {
	private static final int MINIMAL_COMISSION_FEE = 500;
	private static final float COMISSION_FEE_PERCENTAGE = 0.005f;
	private static final float MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE = 0.8f;
	private static final float MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE = 0.98f;
	private static final float MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE = 1.02f;
	private static final float ONE_HUNDRED_PERCENT = 1.0f;
	
	public static int randomShareToBuySellQuantity(int requestedQuantity){
		int offeredQuantity = 0;
		do{
			Float offeredQuantityPercentage = Randomizer.randomFloat(MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE, ONE_HUNDRED_PERCENT);
			offeredQuantity = (int) Math.round(requestedQuantity *  offeredQuantityPercentage);
		}while(offeredQuantity > requestedQuantity || offeredQuantity < (requestedQuantity * MINIMAL_SHARE_SELL_BUY_RANDOM_QUANTITY_PERCENTAGE));
		
		return offeredQuantity;
	}
	
	public static int randomShareSellPrice(int stockPrice){
		int offeredPrice = 0;
		do{
			Float offeredPricePercentage = Randomizer.randomFloat(MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE, ONE_HUNDRED_PERCENT);
			offeredPrice = (int) Math.round(stockPrice * offeredPricePercentage);
		}while(offeredPrice > stockPrice || offeredPrice < (stockPrice * MINIMAL_SHARE_SELL_RANDOM_PRICE_PERCENTAGE));
		
		return offeredPrice;
	}
	
	public static int randomShareBuyPrice(int stockPrice){
		int offeredPrice = 0;
		do{
			Float offeredPricePercentage = Randomizer.randomFloat(ONE_HUNDRED_PERCENT, MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE);
			offeredPrice = (int) Math.round(stockPrice * offeredPricePercentage);
		}while(offeredPrice < stockPrice || offeredPrice > (stockPrice * MAXIMUM_SHARE_BUY_RANDOM_PRICE_PERCENTAGE));
		
		return offeredPrice;
	}
	
	public static int calculateComission(int offeredQuantity, int offeredPrice){

		if(Math.round(offeredQuantity * offeredPrice * COMISSION_FEE_PERCENTAGE) > MINIMAL_COMISSION_FEE){
			return (int) Math.round(offeredQuantity * offeredPrice * COMISSION_FEE_PERCENTAGE);
		}
		return MINIMAL_COMISSION_FEE;
	}
	
	public static void validateBuyRequest(OfferTo offer) throws BrokerActionNoValid{
		if(offer.getActionType() != ActionType.BUY || offer.getStatus() != OfferStatus.ACTIVE){
			throw new BrokerActionNoValid("Buy request not valid - wrong action type or offer not active");
		}
	}
	
	public static void validateSellRequest(OfferTo offer) throws BrokerActionNoValid{
		if(offer.getActionType() != ActionType.SELL || offer.getStatus() != OfferStatus.ACTIVE){
			throw new BrokerActionNoValid("Sell request not valid - wrong action type or offer not active");
		}
	}
	
	public static void validateOfferRequest(String share, int quantity) throws BrokerActionNoValid{
		if(share == null || quantity <= 0){
			throw new BrokerActionNoValid("Offer request not valid - share is null or quantity is negative");
		}
	}
}
