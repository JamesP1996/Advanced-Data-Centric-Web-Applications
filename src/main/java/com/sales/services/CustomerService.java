package com.sales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.repositories.CustomerRepository;


@Service
public class CustomerService {

	// Customer Service that links to Customer Repository
	@Autowired
	CustomerRepository cr;
	
	// Find All Customers
	public Iterable<Customer> getAllCustomers() {
		return cr.findAll();	
	}
	
	// Save a Customer
	public void save(Customer c) {
		cr.save(c);
	}
}
