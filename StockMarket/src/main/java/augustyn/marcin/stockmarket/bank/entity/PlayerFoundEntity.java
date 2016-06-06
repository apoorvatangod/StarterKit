package augustyn.marcin.stockmarket.bank.entity;

import java.io.Serializable;
import javax.persistence.*;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="quantity")
	private Integer quantity;

	public PlayerFoundEntity() {
	}

	public PlayerFoundEntity(Long id, String currency, Integer quantity) {
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