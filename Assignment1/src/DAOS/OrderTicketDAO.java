package DAOS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Customer;
import entities.MenuItem;
import entities.OrderTicket;

public class OrderTicketDAO {
	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("ThomasPU");
	
	public OrderTicketDAO() {
	}
	
	public void persistOrderTicket(OrderTicket orderTicket) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(orderTicket);
		em.getTransaction().commit();
		em.close();
	}
	
	public OrderTicket getOrderTicketByID(int ID) {
		EntityManager em = emf.createEntityManager();
		List<OrderTicket> orderTickets = (List<OrderTicket>)em.createNamedQuery("OrderTicket.findById").setParameter("id", ID).getResultList();
		em.close();
		
		OrderTicket orderTicket = new OrderTicket();
		for(OrderTicket s: orderTickets) {
			orderTicket = s;
		}
		return orderTicket;
	}
	
	public OrderTicket getOrderTicketByCustomer(Customer customer) {
		EntityManager em = emf.createEntityManager();
		List<OrderTicket> orderTickets = (List<OrderTicket>)em.createNamedQuery("OrderTicket.findByCustomer").setParameter("customerID", customer.getId()).getResultList();
		em.close();
		
		OrderTicket orderTicket = new OrderTicket();
		for(OrderTicket s: orderTickets) {
			orderTicket = s;
		}
		return orderTicket;
	}
	
	public OrderTicket getOrderTicketByPrice(double priceTotal) {
		EntityManager em = emf.createEntityManager();
		List<OrderTicket> orderTickets = (List<OrderTicket>)em.createNamedQuery("OrderTicket.findByPrice").setParameter("priceTotal", priceTotal).getResultList();
		em.close();
		
		OrderTicket orderTicket = new OrderTicket();
		for(OrderTicket s: orderTickets) {
			orderTicket = s;
		}
		return orderTicket;
	}
	
	public boolean removeOrderTicket(OrderTicket orderTicket) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.remove(em.contains(orderTicket) ? orderTicket : em.merge(orderTicket));
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public void updateOrderTicketCustomer(OrderTicket orderTicket, Customer cust) {
		EntityManager em = emf.createEntityManager();
		orderTicket.setCustomer(cust.getId());
		em.getTransaction().begin();
		em.merge(orderTicket);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateOrderTicketMenuItem(OrderTicket orderTicket, MenuItem menu) {
		EntityManager em = emf.createEntityManager();
		orderTicket.addMenuItem(menu);
		orderTicket.addToTotal(menu.getPrice());
		em.getTransaction().begin();
		em.merge(orderTicket);
		em.getTransaction().commit();
		em.close();
	}
	
	public void changeOrderTicketMenuItems(OrderTicket orderTicket, ArrayList<MenuItem> menu) {
		EntityManager em = emf.createEntityManager();
		orderTicket.setMenuItems(menu);
		em.getTransaction().begin();
		em.merge(orderTicket);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateOrderTicketPriceTotal(OrderTicket orderTicket, double priceTotal) {
		EntityManager em = emf.createEntityManager();
		orderTicket.addToTotal(priceTotal);
		em.getTransaction().begin();
		em.merge(orderTicket);
		em.getTransaction().commit();
		em.close();
	}
	
	public void changeOrderTicketPriceTotal(OrderTicket orderTicket, double priceTotal) {
		EntityManager em = emf.createEntityManager();
		orderTicket.setPriceTotal(priceTotal);
		em.getTransaction().begin();
		em.merge(orderTicket);
		em.getTransaction().commit();
		em.close();
	}

	public List<OrderTicket> getAllOrders() {
		EntityManager em = emf.createEntityManager();
		List<OrderTicket> orderTickets = (List<OrderTicket>)em.createNamedQuery("OrderTicket.findAll").getResultList();
		em.close();
		return (List<OrderTicket>) orderTickets;
	}
}
