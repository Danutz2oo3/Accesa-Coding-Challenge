package com.accesa.pricecomparator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductControllerBean implements ProductController {
	
	private final ProductService productService;
	
	@Override
	public List<Product> loadProducts(String path) {
		return productService.loadProducts(path);
	}

}
