package augustyn.marcin.stockmarket.broker.to;

import java.io.Serializable;
import java.util.Date;

import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;



public class OfferTo implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private ActionType actionType;
	private String share;
	private int quantity;
	private int price;
	private Date date;
	private Currency currency;
	private int comission;
	private OfferStatus status;

	public OfferTo() {
	}

	public OfferTo(Long id, ActionType actionType, String share, int quantity, int price, Date date, Currency currency,
			int comission, OfferStatus status) {
		this.id = id;
		this.actionType = actionType;
		this.share = share;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
		this.currency = currency;
		this.comission = comission;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getComission() {
		return comission;
	}

	public void setComission(int comission) {
		this.comission = comission;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}
	
}