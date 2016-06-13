package augustyn.marcin.stockmarket.broker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="offer")
@NamedQuery(name="OfferEntity.findAll", query="SELECT o FROM OfferEntity o")
public class OfferEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="action_type")
	@Enumerated(EnumType.STRING)
	private ActionType actionType;
	
	@Column(name="share")
	private String share;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="price")
	private int price;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@Column(name="comission")
	private int comission;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private OfferStatus status;

	public OfferEntity() {
	}

	public OfferEntity(Long id, ActionType actionType, String share, int quantity, int price, Date date, Currency currency,
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