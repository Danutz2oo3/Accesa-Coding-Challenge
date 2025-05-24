package com.accesa.pricecomparator.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.accesa.pricecomparator.model.Product;

public class CsvProductReader {

	public static List<Product> readProducts(String filePath){
		List<Product> products = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			String line;
			br.readLine();
			while((line = br.readLine()) != null) {
				String[] tokens = line.split(";");
				if (tokens.length < 8) continue;
				
				Product product = new Product(
						tokens[0],
                        tokens[1],
                        tokens[2],
                        tokens[3],
                        Double.parseDouble(tokens[4]),
                        tokens[5],
                        Double.parseDouble(tokens[6]),
                        tokens[7]
				);
				products.add(product);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
}
