package com.accesa.pricecomparator.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.dto.product.PricePointDto;

@RequestMapping("/api")
public interface ProductController {

	@GetMapping("/products/{productId}/price-history")
    public ResponseEntity<List<PricePointDto>> getPriceHistory(
    	@PathVariable String productId,
        @RequestParam(required = false) String store,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String category
    );
}
