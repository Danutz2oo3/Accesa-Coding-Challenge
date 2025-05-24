package com.accesa.pricecomparator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

	private String productId;
	
	private String productName;
	
	private String category;
	
	private String brand;
	
	private double quantity;
	
	private String unit;
	
	private double price;
	
	private String currency;
}
