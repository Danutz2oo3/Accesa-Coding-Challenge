package com.accesa.pricecomparator.controller.pricealert;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.dto.product.PricePointDto;

@RequestMapping("/api/alerts")
public interface PriceAlertController {
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String productId, @RequestParam double targetPrice);
	
	
	@GetMapping("/check")
    public ResponseEntity<List<PricePointDto>> check(@RequestParam String date);
}
