package augustyn.marcin.stockmarket.broker.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="action_type")
	private String actionType;
	
	@Column(name="share")
	private String share;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="comission")
	private Integer comission;
	
	@Column(name="status")
	private String status;

	public OfferEntity() {
	}

	public OfferEntity(Long id, String actionType, String share, Integer quantity, Integer price, Date date, String currency,
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