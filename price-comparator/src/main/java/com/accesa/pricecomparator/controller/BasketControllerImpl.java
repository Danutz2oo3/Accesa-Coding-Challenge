package com.accesa.pricecomparator.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.ShoppingBasketRequest;
import com.accesa.pricecomparator.dto.ShoppingBasketResponse;
import com.accesa.pricecomparator.service.BasketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BasketControllerImpl implements BasketController {
	
	private final BasketService basketService;

	@Override
	public ShoppingBasketResponse check(ShoppingBasketRequest request) {
		LocalDate date = LocalDate.parse(request.getDate());
        return basketService.evaluateBasket(request.getProductIds(), date);
	}

}
