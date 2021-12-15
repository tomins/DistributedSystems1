package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "MenuItem.findAll", query = "select o from MenuItem o"),
	@NamedQuery(name = "MenuItem.findByID", query = "select o from MenuItem o where o.id=:id"),
	@NamedQuery(name = "MenuItem.findByfindByName", query = "select o from MenuItem o where o.name=:name")
	})
@Entity
public class MenuItem {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	
	public MenuItem() {
		
	}
	public MenuItem(String name, String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
