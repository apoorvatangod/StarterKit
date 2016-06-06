package augustyn.marcin.stockmarket.bank.to;

import java.io.Serializable;


public class PlayerFoundTo implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private String currency;
	private Integer quantity;

	public PlayerFoundTo() {
	}

	public PlayerFoundTo(Long id, String currency, Integer quantity) {
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
	
}