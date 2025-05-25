package com.accesa.pricecomparator.dto;

import java.util.List;

import lombok.Data;

@Data
public class ShoppingBasketRequest {
	
	private List<String> productIds;
	private String date;
}
