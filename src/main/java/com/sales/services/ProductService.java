package com.sales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Product;
import com.sales.repositories.ProductRepository;

// Product Service for All Products. Connects to Product Repository
@Service
public class ProductService {

	@Autowired
	ProductRepository pr;
	
	// Iterate over all Products
	public Iterable<Product> getAllProducts() {
		return pr.findAll();	
	}
	// Save a Product
	public void save(Product p) {
		pr.save(p);
	}

}
