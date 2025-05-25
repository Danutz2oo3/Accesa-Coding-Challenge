package com.accesa.pricecomparator.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.product.PricePointDto;
import com.accesa.pricecomparator.service.productanalytics.ProductAnalyticsService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

	private final ProductAnalyticsService analyticsService;
	
	@Override
	public ResponseEntity<List<PricePointDto>> getPriceHistory(String productId, String store, String brand, String category) {
		return ResponseEntity.ok(analyticsService.getPriceHistory(productId, store, brand, category));
	}

}
