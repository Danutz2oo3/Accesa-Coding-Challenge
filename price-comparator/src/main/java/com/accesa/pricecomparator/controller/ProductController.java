package com.accesa.pricecomparator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.model.Product;

@RequestMapping("/api/products")
public interface ProductController {
	
	@GetMapping("/load")
	public List<Product> loadProducts(@RequestParam String path);
	
}
