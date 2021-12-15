package DAOS;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.MenuItem;

public class MenuItemDAO {
	
	private static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("ThomasPU");
	
	public MenuItemDAO() {
	}
	
	public void persistMenuItem(MenuItem menuItem) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(menuItem);
		em.getTransaction().commit();
		em.close();
	}
	
	public MenuItem getMenuItemById(int id) {
		EntityManager em = emf.createEntityManager();
		List<MenuItem> menuItems = (List<MenuItem>)em.createNamedQuery("MenuItem.findByID").setParameter("id", id).getResultList();
		em.close();
		
		MenuItem menuItem = new MenuItem();
		for(MenuItem s: menuItems) {
			menuItem = s;
		}
		return menuItem;
	}
	
	public MenuItem getMenuItemByName(String name) {
		EntityManager em = emf.createEntityManager();
		List<MenuItem> menuItems = (List<MenuItem>)em.createNamedQuery("MenuItem.findByName").setParameter("name", name).getResultList();
		em.close();
		
		MenuItem menuItem = new MenuItem();
		for(MenuItem s: menuItems) {
			menuItem = s;
		}
		return menuItem;
	}
	
	public boolean removeMenuItem(MenuItem menuItem) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.remove(em.contains(menuItem) ? menuItem : em.merge(menuItem));
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public void updateMenuItemName(MenuItem menuItem, String name) {
		EntityManager em = emf.createEntityManager();
		menuItem.setName(name);
		em.getTransaction().begin();
		em.merge(menuItem);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateMenuItemDescription(MenuItem menuItem, String description) {
		EntityManager em = emf.createEntityManager();
		menuItem.setDescription(description);
		em.getTransaction().begin();
		em.merge(menuItem);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateMenuItemPrice(MenuItem menuItem, double price) {
		EntityManager em = emf.createEntityManager();
		menuItem.setPrice(price);
		em.getTransaction().begin();
		em.merge(menuItem);
		em.getTransaction().commit();
		em.close();
	}

	public List<MenuItem> getAllItems() {
		EntityManager em = emf.createEntityManager();
		List<MenuItem> menuItems = (List<MenuItem>)em.createNamedQuery("MenuItem.findAll").getResultList();
		em.close();
		
		return menuItems;
		
	}
}
