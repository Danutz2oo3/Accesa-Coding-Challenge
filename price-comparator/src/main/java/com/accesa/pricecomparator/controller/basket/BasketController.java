package com.accesa.pricecomparator.controller.basket;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.dto.basket.OptimizeBasketRequest;
import com.accesa.pricecomparator.dto.basket.OptimizeBasketResponse;
import com.accesa.pricecomparator.dto.product.SubstituteProductDto;

@RequestMapping("/api/basket")
public interface BasketController {
	
	@PostMapping("/optimize")
	public ResponseEntity<OptimizeBasketResponse> optimizeBasket(@RequestBody OptimizeBasketRequest request);
	
	@GetMapping("/substitutes")
	public ResponseEntity<List<SubstituteProductDto>> getSubstitutes(
	    @RequestParam String productId,
	    @RequestParam String date
	);
	
}
