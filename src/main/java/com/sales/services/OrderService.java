package com.sales.services;
 
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sales.models.Order;
import com.sales.repositories.OrderRepository;

// Order Service that links to Order Repository
@Service
public class OrderService {
	@Autowired
	OrderRepository or;
	
	// Iterate over all Orders
	public Iterable<Order> getAllOrders() {
		return or.findAll();	
	}
	
	// Save a Order
	public void save(Order o) {
		// Set the Order Date that is being saved. To the Current LocalDate now which has the format yy-MM-DD e.g.(2020-03-17)
		o.setOrderDate(LocalDate.now().toString());
		or.save(o);
	}
}
