package com.accesa.pricecomparator.service.basket;

import java.time.LocalDate;
import java.util.List;

import com.accesa.pricecomparator.dto.basket.OptimizeBasketResponse;
import com.accesa.pricecomparator.dto.product.SubstituteProductDto;

public interface BasketService {
	
	public abstract OptimizeBasketResponse optimizeBasket(List<String> productIds, LocalDate date);
	
	List<SubstituteProductDto> getSubstitutes(String productId, LocalDate date);
	
}
