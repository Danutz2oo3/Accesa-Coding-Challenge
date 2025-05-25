package com.accesa.pricecomparator.service.pricealert;

import java.time.LocalDate;
import java.util.List;

import com.accesa.pricecomparator.dto.product.PricePointDto;

public interface PriceAlertService {
	
	public void registerAlert(String productId, double targetPrice);
	
	public List<PricePointDto> checkAlerts(LocalDate date);
	
}
