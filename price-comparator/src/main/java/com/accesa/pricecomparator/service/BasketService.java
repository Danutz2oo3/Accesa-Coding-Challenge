package com.accesa.pricecomparator.service;

import java.time.LocalDate;
import java.util.List;

import com.accesa.pricecomparator.dto.ShoppingBasketResponse;

public interface BasketService {
	
	public abstract ShoppingBasketResponse evaluateBasket(List<String> productIds, LocalDate date);
	
}
