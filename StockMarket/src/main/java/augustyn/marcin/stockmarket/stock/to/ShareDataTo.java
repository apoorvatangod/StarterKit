package augustyn.marcin.stockmarket.stock.to;

import java.io.Serializable;
import java.util.Date;


public class ShareDataTo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer price;
	private Date date;

	public ShareDataTo() {
	}

	public ShareDataTo(Long id, String name, Integer price, Date date) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}