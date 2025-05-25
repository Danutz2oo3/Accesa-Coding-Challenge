package com.accesa.pricecomparator.controller.pricealert;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.product.PricePointDto;
import com.accesa.pricecomparator.service.pricealert.PriceAlertService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PriceAlertControllerImpl implements PriceAlertController{

	private final PriceAlertService alertService;
	
	@Override
	public ResponseEntity<String> register(String productId, double targetPrice) {
		alertService.registerAlert(productId, targetPrice);
        return ResponseEntity.ok("Alert registered for " + productId);
	}

	@Override
	public ResponseEntity<List<PricePointDto>> check(String date) {
		return ResponseEntity.ok(alertService.checkAlerts(LocalDate.parse(date)));
	}

}
