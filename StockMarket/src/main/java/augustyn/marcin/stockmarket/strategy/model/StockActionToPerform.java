package augustyn.marcin.stockmarket.strategy.model;

import java.util.Date;

import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;

public class StockActionToPerform {
	
	private ActionType type;
	private String shareName;
	private Integer quantity;
	private Integer minPrice;
	private Integer maxPrice;
	private Currency currency;
	private Date date;
	
	public StockActionToPerform(ActionType type, String shareName, Integer quantity, Integer minPrice, Integer maxPrice,
			Currency currency, Date date) {
		this.type = type;
		this.shareName = shareName;
		this.quantity = quantity;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.currency = currency;
		this.date = date;
	}

	public ActionType getType() {
		return type;
	}

	public String getShareName() {
		return shareName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Date getDate() {
		return date;
	}
}
