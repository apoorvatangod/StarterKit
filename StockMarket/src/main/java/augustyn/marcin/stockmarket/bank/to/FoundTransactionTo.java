package augustyn.marcin.stockmarket.bank.to;

import java.io.Serializable;
import java.util.Date;


public class FoundTransactionTo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	
	private String type;
	private String currency;
	private Integer quantity;
	private Date date;

	public FoundTransactionTo() {
	}

	public FoundTransactionTo(Long id, String type, String currency, Integer quantity, Date date) {
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