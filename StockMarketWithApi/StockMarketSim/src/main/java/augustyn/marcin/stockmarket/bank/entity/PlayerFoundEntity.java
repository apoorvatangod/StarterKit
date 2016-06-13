package augustyn.marcin.stockmarket.bank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import augustyn.marcin.stockmarket.enumation.Currency;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="player_found")
@NamedQuery(name="PlayerFoundEntity.findAll", query="SELECT pf FROM PlayerFoundEntity pf")
public class PlayerFoundEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@Column(name="quantity")
	private int quantity;

	public PlayerFoundEntity() {
	}

	public PlayerFoundEntity(Long id, Currency currency, int quantity) {
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