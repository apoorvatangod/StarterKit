package augustyn.marcin.stockmarket.broker.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="player_share")
@NamedQuery(name="PlayerShareEntity.findAll", query="SELECT ps FROM PlayerShareEntity ps")
public class PlayerShareEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="quantity")
	private Integer quantity;

	public PlayerShareEntity() {
	}

	
	public PlayerShareEntity(Long id, String name, Integer quantity) {
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


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}