package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query = "select o from Customer o"),
	@NamedQuery(name = "Customer.findByID", query = "select o from Customer o where o.id=:id"),
	@NamedQuery(name = "Customer.findByName", query = "select o from Customer o where o.name=:name")
	})
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String email;
	private String phoneNum;
	private String password;
	
	public Customer() {
		
	}
	
	public Customer(String name, String address, String email, String phoneNum, String password) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNum = phoneNum;
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
