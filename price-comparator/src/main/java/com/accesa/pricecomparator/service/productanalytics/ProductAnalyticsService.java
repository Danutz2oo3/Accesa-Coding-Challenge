package com.accesa.pricecomparator.service.productanalytics;

import java.util.List;

import com.accesa.pricecomparator.dto.product.PricePointDto;

public interface ProductAnalyticsService {

	public List<PricePointDto> getPriceHistory(String productId, String store, String brand, String category);
	
}
