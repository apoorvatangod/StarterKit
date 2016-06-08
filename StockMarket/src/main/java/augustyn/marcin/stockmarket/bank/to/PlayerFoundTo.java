package augustyn.marcin.stockmarket.bank.to;

import java.io.Serializable;

import augustyn.marcin.stockmarket.enumation.Currency;


public class PlayerFoundTo implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private Currency currency;
	private int quantity;

	public PlayerFoundTo() {
	}

	public PlayerFoundTo(Long id, Currency currency, int quantity) {
		this.id = id;
		this.currency = currency;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}