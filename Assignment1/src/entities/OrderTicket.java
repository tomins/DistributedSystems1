package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({
	@NamedQuery(name = "OrderTicket.findAll", query = "select o from OrderTicket o"),
	@NamedQuery(name = "OrderTicket.findById", query = "select o from OrderTicket o where o.id=:id"),
	@NamedQuery(name = "OrderTicket.findByCustomer", query = "select o from OrderTicket o where o.customerID=:customerID"),
	@NamedQuery(name = "OrderTicket.findByPrice", query = "select o from OrderTicket o where o.priceTotal=:priceTotal")
	})
@Entity
public class OrderTicket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private int customerID;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<MenuItem> menuItems;
	
	private double priceTotal;
	
	public OrderTicket() {
		
	}
	public OrderTicket(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public OrderTicket(int customer, List<MenuItem> menuItems, double price) {
		super();
		this.customerID = customer;
		this.menuItems = menuItems;
		this.priceTotal = price;
	}
	public int getId() {
		return id;
	}
	public int getCustomer() {
		return this.customerID;
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public void addMenuItem(MenuItem item) {
		menuItems.add(item);
	}
	
	public double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}
	public void addToTotal(double addPrice) {
		this.priceTotal = this.priceTotal + addPrice;
	}
	public void setCustomer(int id2) {
		this.customerID = id2;
		
	}
	
	
}
