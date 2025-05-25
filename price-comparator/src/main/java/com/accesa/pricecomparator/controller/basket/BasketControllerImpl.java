package com.accesa.pricecomparator.controller.basket;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.basket.OptimizeBasketRequest;
import com.accesa.pricecomparator.dto.basket.OptimizeBasketResponse;
import com.accesa.pricecomparator.dto.product.SubstituteProductDto;
import com.accesa.pricecomparator.service.basket.BasketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BasketControllerImpl implements BasketController {
	
	private final BasketService basketService;

	@Override
	public ResponseEntity<OptimizeBasketResponse> optimizeBasket(OptimizeBasketRequest request) {
		LocalDate date = request.date() != null ? request.date() : LocalDate.now();
	    return ResponseEntity.ok(basketService.optimizeBasket(request.productIds(), date));
	}

	@Override
	public ResponseEntity<List<SubstituteProductDto>> getSubstitutes(String productId, String date) {
		 return ResponseEntity.ok(basketService.getSubstitutes(productId, LocalDate.parse(date)));
	}

}
