package com.accesa.pricecomparator.dto.basket;

public record OptimizedItemDto(
	    String productId,
	    String productName,
	    String store,
	    double basePrice,
	    double finalPrice,
	    double discountPercent,
	    boolean discounted
	) {}
