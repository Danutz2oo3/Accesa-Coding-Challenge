package com.accesa.pricecomparator.service;

import java.util.List;

import com.accesa.pricecomparator.model.Product;


public interface ProductService {
	
	public List<Product> loadProducts(String storeCsvFilePath);
	
}
