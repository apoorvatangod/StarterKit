package augustyn.marcin.stockmarket.bank.entity;

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
@Table(name="found_transaction")
@NamedQuery(name="FoundTransactionEntity.findAll", query="SELECT ft FROM FoundTransactionEntity ft")
public class FoundTransactionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="date")
	private Date date;

	public FoundTransactionEntity() {
	}

	public FoundTransactionEntity(Long id, String type, String currency, Integer quantity, Date date) {
		this.id = id;
		this.type = type;
		this.currency = currency;
		this.quantity = quantity;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}