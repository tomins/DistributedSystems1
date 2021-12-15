package DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Customer;

public class CustomerDAO {

	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ThomasPU");
	
	public CustomerDAO() {
		
	}
	
	public void persistCustomer(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public Customer getCustomerByID(int id) {
		EntityManager em = emf.createEntityManager();
		List<Customer> customers = (List<Customer>)em.createNamedQuery("Customer.findByID").setParameter("id", id).getResultList();
		em.close();
		
		Customer cust = new Customer();
		for(Customer s: customers) {
			cust = s;
		}
		return cust;
	}
	public Customer getCustomerByName(String name) {
		EntityManager em = emf.createEntityManager();
		List<Customer> customers = (List<Customer>)em.createNamedQuery("Customer.findByName").setParameter("name", name).getResultList();
		em.close();
		
		Customer cust = new Customer();
		for(Customer s: customers) {
			cust = s;
		}
		return cust;
	}
	
	public boolean removeCustomer(Customer customer) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.remove(em.contains(customer) ? customer : em.merge(customer));
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public void updateCustomerName(Customer customer, String name) {
		EntityManager em = emf.createEntityManager();
		customer.setName(name);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateCustomerAddress(Customer customer, String address) {
		EntityManager em = emf.createEntityManager();
		customer.setAddress(address);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateCustomerEmail(Customer customer, String email) {
		EntityManager em = emf.createEntityManager();
		customer.setEmail(email);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateCustomerPhoneNum(Customer customer, String phoneNum) {
		EntityManager em = emf.createEntityManager();
		customer.setPhoneNum(phoneNum);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateCustomerPassword(Customer customer, String password) {
		EntityManager em = emf.createEntityManager();
		customer.setPassword(password);
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		em.close();
	}

	public List<Customer> getAllOrders() {
		EntityManager em = emf.createEntityManager();
		List<Customer> customers = (List<Customer>)em.createNamedQuery("Customer.findAll").getResultList();
		em.close();
		
		return customers;
	}
}
