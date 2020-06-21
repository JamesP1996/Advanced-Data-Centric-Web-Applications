package com.sales.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
public class MainController {
	
	//Create the Auto Wired Services for Each Data Table/Model
	@Autowired
	ProductService ps;
	
	@Autowired 
	CustomerService cs;
	
	@Autowired
	OrderService os;
	
	// When the Request is a Show Products GET. Iterate through all the Products in the Products Service and Add them to the model for Display.
	@RequestMapping(value = "/showProducts.html")
	public String getProducts(Model model) {
		Iterable<Product> products = ps.getAllProducts();
		model.addAttribute("products",products);
		return "showProducts";
	}
	
	// Get the Attributes entered on the Add Product Page and create a New Product
	@RequestMapping(value="/addProduct.html", method=RequestMethod.GET)
	public String addProductGET(Model model) {
		Product pr = new Product();
		model.addAttribute("product",pr);
		
		return "addProduct";
	}
	
	// Post the Products to the database model/table, if it has errors. Return the Add Products Page with Relevant Errors.
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String addProductPOST(@Valid @ModelAttribute("product")Product pr,BindingResult result) {
		
		if (result.hasErrors()) {
			return "addProduct";
		}
		
		ps.save(pr);
		return "redirect:showProducts.html";
		
	}
	
	// When the Request is a Show Customers GET. Iterate through all the Products in the Customers Service and Add them to the model for Display.
	@RequestMapping(value = "/showCustomers.html")
	public String getCustomers(Model model) {
		Iterable<Customer> customers = cs.getAllCustomers();
		model.addAttribute("customers",customers); 
		return "showCustomers";
	}
	
	// Get the Attributes entered on the Add Customer Page and create a New Customer
	@RequestMapping(value="/addCustomer.html", method=RequestMethod.GET)
	public String addCustomerGET(Model model) {
		Customer cr = new Customer();
		model.addAttribute("customer",cr);
		
		return "addCustomer";
	}
	
	// Post the Customers to the database model/table, if it has errors. Return the Add Customers Page with Relevant Errors.
	@RequestMapping(value="/addCustomer.html", method=RequestMethod.POST)
	public String addCustomerPOST(@Valid @ModelAttribute("customer")Customer cr, BindingResult result) {
		if(result.hasErrors()) {
			return "addCustomer";
		}
		
		cs.save(cr);
		return "redirect:showCustomers.html";
	}
	
	// When the Request is a Show Orders GET. Iterate through all the Products in the Orders Service and Add them to the model for Display.
	@RequestMapping(value = "/showOrders.html")
	public String getOrders(Model model) {
		Iterable<Order> orders = os.getAllOrders();
		model.addAttribute("orders",orders); 
		return "showOrders";
	}
	
	// Get the Attributes entered on the Add Orders Page and create a New Order, Also Iterate through a Customer and Products List
	// In Order to display who made what orders, on what products.
	@RequestMapping(value="/newOrder.html", method=RequestMethod.GET)
	public String addOrdersGET(Model model) {
		
		Iterable<Customer> customers = cs.getAllCustomers();
		Iterable<Product> products = ps.getAllProducts();
		
		Map<Long,String> customerList = new LinkedHashMap<Long, String>();
		Map<Long,String> productList = new LinkedHashMap<Long, String>();
		
		for (Customer c : customers) {
			customerList.put(c.getcId(),c.getcName());
		}
		for (Product p : products) {
			productList.put(p.getpId(),p.getpDesc());
		}
		
		Order or = new Order();
		model.addAttribute("order",or);
		model.addAttribute("customerList",customerList);
		model.addAttribute("productList",productList);
		
		return "newOrder";
	}
	
	// Post the Orders to the database model/table, if it has errors. Return the Add Orders Page with Relevant Errors.
	@RequestMapping(value="/newOrder.html", method=RequestMethod.POST)
	public String addOrdersPOST(@Valid @ModelAttribute("order")Order or, BindingResult result, Model model, HttpServletRequest req) {
		
		
		
		// Have to Re-Iterate Over The Lists and Add Them Back in Model as a Bug Occurs during Post after Error that removes the lists from Drop Down.
		if(result.hasErrors()) {
			Iterable<Customer> customers = cs.getAllCustomers();
			Iterable<Product> products = ps.getAllProducts();
			
			Map<Long,String> customerList = new LinkedHashMap<Long, String>();
			Map<Long,String> productList = new LinkedHashMap<Long, String>();
			for (Customer c : customers) {
				customerList.put(c.getcId(),c.getcName());
			}
			for (Product p : products) {
				productList.put(p.getpId(),p.getpDesc());
			}
			model.addAttribute("customerList",customerList);
			model.addAttribute("productList",productList);
			return "newOrder";
		}
		
		// Use a Try Catch and If to manage Error Exceptions.
		try {
		// If the Customer and Product Exist. Minus the Order QTY from the Product QTY in Stock and Save the Data to the Repository
		if(or.getProd() != null && or.getCust() != null) {
			int productInStock = or.getProd().getQtyInStock();
			int amountbought = or.getQty();
			or.getProd().setQtyInStock(productInStock - amountbought);
			os.save(or);
			
		}
		// Else Pass In The Customer and Product Parameters from the Page. Add them to the model and display a error page on the same URL.
		// Saying that the Customer/Product was not found on the database anymore. (Does Not Save because of this Error)
		else {
			String cId = req.getParameter("cust");
			String pId = req.getParameter("prod");
			Order order = or;
			model.addAttribute("order",order);
			model.addAttribute("cId",cId);
			model.addAttribute("pId",pId);
			return "orderError2";
		}
		
		}
		// If a Transaction Exception Happens. Return a Order Error Saying that the Quantity of the Order is to Large for
		// the currently remaining quantity of that product in stock.
		catch(org.springframework.transaction.TransactionSystemException ts){
			Order order = or;
			int productInStock = or.getProd().getQtyInStock();
			model.addAttribute("order",order);
			model.addAttribute("stock",productInStock);

			
			return "orderError";
		}
		
		//Catch Other Exceptions and Display them to Console (There should not be any other errors as we have caught the possible ones).
		catch(Exception e){
			System.out.println(e);
		}
		return "redirect:showOrders.html";
		
	}
	
	

}
