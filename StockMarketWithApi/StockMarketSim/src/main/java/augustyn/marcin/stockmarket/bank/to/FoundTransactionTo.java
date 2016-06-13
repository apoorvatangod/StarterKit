package augustyn.marcin.stockmarket.bank.to;

import java.io.Serializable;
import java.util.Date;

import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;


public class FoundTransactionTo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;	
	private TransactionType type;
	private Currency currency;
	private int quantity;
	private Date date;

	public FoundTransactionTo() {
	}

	public FoundTransactionTo(Long id, TransactionType type, Currency currency, int quantity, Date date) {
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

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}