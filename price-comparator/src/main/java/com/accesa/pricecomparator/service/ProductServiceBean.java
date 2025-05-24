package com.accesa.pricecomparator.service;

import java.util.List;

import com.accesa.pricecomparator.model.Product;
import com.accesa.pricecomparator.utils.CsvProductReader;

public class ProductServiceBean implements ProductService {

	@Override
	public List<Product> loadProducts(String storeCsvFilePath) {
		return CsvProductReader.readProducts(storeCsvFilePath);
	}
}
