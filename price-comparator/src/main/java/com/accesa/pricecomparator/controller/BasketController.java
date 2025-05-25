package com.accesa.pricecomparator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accesa.pricecomparator.dto.ShoppingBasketRequest;
import com.accesa.pricecomparator.dto.ShoppingBasketResponse;

@RequestMapping("/api/basket")
public interface BasketController {
	
	@PostMapping("/check")
    public ShoppingBasketResponse check(@RequestBody ShoppingBasketRequest request);
	
}
