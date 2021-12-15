package example;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Customer;
import entities.MenuItem;
import entities.OrderTicket;


import DAOS.CustomerDAO;
import DAOS.MenuItemDAO;
import DAOS.OrderTicketDAO;

@Path("/hello")
public class MainFrame {
	
	private static CustomerDAO custDAO = new CustomerDAO();
	private static MenuItemDAO menuItemDAO = new MenuItemDAO();
	private static OrderTicketDAO orderDAO = new OrderTicketDAO();
	
	
	@GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
		ArrayList<Customer> customers1 = new ArrayList<Customer>();
		customers1 = (ArrayList<Customer>) custDAO.getAllOrders();
		String customersList1 = "";
		for (Customer c: customers1) {
			customersList1 = customersList1 + c.getEmail() + "\n";
		}
	    return "<html> " + "<title>" + "Welcome To Assignment 1" + "</title>"
	        + "<body><h1>" + customersList1 + "</body></h1>" + "</html> ";
	  }
	
	@GET
	@Path("/customers")
	@Produces(MediaType.TEXT_HTML)
	public String listCustomers(){//need to change to a string
		ArrayList<Customer> customers1 = new ArrayList<Customer>();
		customers1 = (ArrayList<Customer>) custDAO.getAllOrders();
		String customersList1 = "";
		for (Customer c: customers1) {
			customersList1 = customersList1 + c.getEmail() + "<br>";
		}
        return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + customersList1 + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/orders")
	@Produces(MediaType.TEXT_HTML)
	public String listOrders(){
		ArrayList<OrderTicket> orderTic = new ArrayList<OrderTicket>();
		orderTic = (ArrayList<OrderTicket>) orderDAO.getAllOrders();
		String ordersList1 = "";
		for (OrderTicket ot: orderTic) {
			ordersList1 = ordersList1 + ot.getId() + " | " + ot.getCustomer() + " | " + ot.getPriceTotal();
			List<MenuItem>menu = (List<MenuItem>) ot.getMenuItems();
			for(MenuItem m: menu) {
				ordersList1 = ordersList1 + " | " + m.getName() + "<br>";
			}
		}
        return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
                + "<body><h1>" + ordersList1 + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/menuitems")
	@Produces(MediaType.TEXT_HTML)
	public String listMenuItems(){
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu = (ArrayList<MenuItem>) menuItemDAO.getAllItems();
		String menuList1 = "";
		for (MenuItem c: menu) {
			menuList1 = menuList1 + c.getName() + "<br>";
		}
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + menuList1 + "</body></h1>" + "</html> ";
		//menu;
    }
	
	@GET
	@Path("/customers/{custID}")
	@Produces(MediaType.TEXT_HTML)
	 public String getCustomer(@PathParam("custID")int custID){
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + custDAO.getCustomerByID(custID).getEmail() + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/orders/{orderID}")
	@Produces(MediaType.TEXT_HTML)
	 public String getOrderTicket(@PathParam("orderID")int orderID){
		List<MenuItem> menus = orderDAO.getOrderTicketByID(orderID).getMenuItems();
		String menuString = "";
		for(MenuItem m: menus) {
			menuString = menuString + m.getName() + "<br>";
		}
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + menuString + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/menuitems/{menuItem}")
	@Produces(MediaType.TEXT_HTML)
	 public String getMenuItem(@PathParam("menuItem")int menuItem){		
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + menuItemDAO.getMenuItemById(menuItem).getName() + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/orders/customer/{custID}")
	@Produces(MediaType.TEXT_HTML)
	 public String getOrderTicketByCustomer(@PathParam("custID")int custID){	
		OrderTicket orderTic = orderDAO.getOrderTicketByCustomer(custDAO.getCustomerByID(custID));
		List<MenuItem> menus = orderTic.getMenuItems();
		String menuString = "";
		for(MenuItem m: menus) {
			menuString = menuString + m.getName() + "<br>";
		}
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1>" + menuString + "</body></h1>" + "</html> ";
    }
	
	@GET
	@Path("/orders/customerPhone/{orderID}")
	@Produces(MediaType.TEXT_HTML)
	 public String getCustomerNumberByOrder(@PathParam("orderID")int orderID){	
		OrderTicket orderTic = orderDAO.getOrderTicketByID(orderID);
		Customer cust = custDAO.getCustomerByID(6);
		String custPhone = cust.getPhoneNum();
		return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
        + "<body><h1> Phone Number for client of order: " + orderID + " : " + custPhone + "</body></h1>" + "</html> ";
    }
	
	@POST
	@Path("/customers")
	@Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newCust(@FormParam("name") String name,
            @FormParam("address") String address,
            @FormParam("email") String email,
            @FormParam("phoneNum") String phoneNum,
            @FormParam("password") String password){
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(email);
			customer.setAddress(address);
			customer.setPassword(password);
			customer.setPhoneNum(phoneNum);
			CustomerDAO custDAO = new CustomerDAO();
			custDAO.persistCustomer(customer);
			return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
	        + "<body><h1>" + customer.getId() + "</body></h1>" + "</html> ";
	}
	
	@POST
	@Path("/menuitem")
	@Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newMenuItem(@FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("price") String price){
			MenuItem menuItem = new MenuItem();
			menuItem.setName(name);
			menuItem.setDescription(description);
			menuItem.setPrice(Integer.parseInt(price));
			MenuItemDAO menuItemDAO = new MenuItemDAO();
			menuItemDAO.persistMenuItem(menuItem);;
			return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
	        + "<body><h1>" + menuItem.getId() + "</body></h1>" + "</html> ";
	}
	
	
	/*@POST
	@Path("/orders")
	@Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String newOrderTicket(@FormParam("customer") String custName,
            @SuppressWarnings("rawtypes") @FormParam("MenuItems") List<String> menuItemNames){
			OrderTicket orderTicket = new OrderTicket();
			
			orderTicket.setCustomer(custDAO.getCustomerByName(custName).getId());
			ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
			for(String s: menuItemNames) {
				MenuItem men = menuItemDAO.getMenuItemByName(s);
				menuItems.add(men);
			}
			orderTicket.setMenuItems(menuItems);;
			int i = 0;
			for(MenuItem m: menuItems) {
				i += m.getPrice();
			}
			orderTicket.setPriceTotal(i);;
			
			orderDAO.persistOrderTicket(orderTicket);
			return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
	        + "<body><h1>" + orderTicket.getId() + "</body></h1>" + "</html> ";
	}*/
	
	@POST
	@Path("/ordersBasic")
	@Produces(MediaType.TEXT_HTML)
	public String newOrderTicket(){
			OrderTicket orderTicket = new OrderTicket();
			
			orderTicket.setCustomer(custDAO.getCustomerByID(0).getId());
			ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
			for(int i = 0;i>3;i++) {
				MenuItem men = menuItemDAO.getMenuItemById(i);
				menuItems.add(men);
			}
			orderTicket.setMenuItems(menuItems);
			int i = 0;
			for(MenuItem m: menuItems) {
				i += m.getPrice();
			}
			orderTicket.setPriceTotal(i);
			
			orderDAO.persistOrderTicket(orderTicket);
			return "<html> " + "<title>" + "Hello2021 Jersey" + "</title>"
	        + "<body><h1>" + orderTicket.getId() + "</body></h1>" + "</html> ";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/customers/{id}")
	public String deleteCustomerByID(@PathParam("id") int id) {
		if (custDAO.removeCustomer(custDAO.getCustomerByID(id))){
			return "This is deleted";
	}
	else {
		return "Error";
	}
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/menuitems/{id}")
	public String deleteMenuItemByID(@PathParam("id") int id) {
		if (menuItemDAO.removeMenuItem(menuItemDAO.getMenuItemById(id))){
			return "This is deleted";
	}
	else {
		return "Error";
	}
	    
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/orders/{id}")
	public String deleteOrderTicketByID(@PathParam("id") int id) {
		if (orderDAO.removeOrderTicket(orderDAO.getOrderTicketByID(id))){
				return "This is deleted";
		}
		else {
			return "Error";
		}
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Path("/customers/update/{custID}/{email}")
	public String updateUserEmail(@PathParam("custID") int id,
			@PathParam("email") String email){
		Customer cust = custDAO.getCustomerByID(id);
		custDAO.updateCustomerEmail(cust, email);
		return "User Updated";
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Path("/orders/update/customer/{orderID}/{custID}")
	public String updateOrderCust(@PathParam("orderID") int id,
			@PathParam("custID") int custID){
		OrderTicket ord = orderDAO.getOrderTicketByID(id);
		Customer cust = custDAO.getCustomerByID(custID);
		orderDAO.updateOrderTicketCustomer(ord, cust);
		return "User Updated";
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Path("/orders/update/menu/{orderID}/{menuID}")
	public String updateOrderMenu(@PathParam("orderID") int id,
			@PathParam("menuID") int menuID){
		OrderTicket ord = orderDAO.getOrderTicketByID(id);
		MenuItem men = menuItemDAO.getMenuItemById(menuID);
		orderDAO.updateOrderTicketMenuItem(ord, men);
		return "Menu Updated";
	}
	
	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Path("/menuitems/update/{menuId}/{price}")
	public String updateMenuItem(@PathParam("menuId") int id,
			@PathParam("price") int price){
		MenuItem men = menuItemDAO.getMenuItemById(id);
		menuItemDAO.updateMenuItemPrice(men, price);
		return "Menu Item Updated";
	}
	
	
	
	
}
