package augustyn.marcin.stockmarket.broker.to;

import java.io.Serializable;


public class PlayerShareTo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private String name;
	private int quantity;

	public PlayerShareTo() {
	}

	
	public PlayerShareTo(Long id, String name, int quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}