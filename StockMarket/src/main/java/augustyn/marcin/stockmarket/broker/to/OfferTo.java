package augustyn.marcin.stockmarket.broker.to;

import java.io.Serializable;
import java.util.Date;



public class OfferTo implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private String actionType;
	private String share;
	private Integer quantity;
	private Integer price;
	private Date date;
	private String currency;
	private Integer comission;
	private String status;

	public OfferTo() {
	}

	public OfferTo(Long id, String actionType, String share, Integer quantity, Integer price, Date date, String currency,
			Integer comission, String status) {
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getComission() {
		return comission;
	}

	public void setComission(Integer comission) {
		this.comission = comission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}