package com.accesa.pricecomparator.service.productanalytics;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.dto.product.PricePointDto;
import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductAnalyticsServiceImpl implements ProductAnalyticsService {

	private final ProductPriceRepository productRepo;
	private final DiscountRepository discountRepo;
	
	@Override
	public List<PricePointDto> getPriceHistory(String productId, String store, String brand, String category) {
		List<ProductPrice> prices = productRepo.findPriceHistory(productId, store, brand, category);

	    return prices.stream().map(p -> {
	        double base = p.getPrice();

	        Optional<Discount> discount = discountRepo.findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
	                p.getProductId(), p.getStore(), p.getDate(), p.getDate());

	        if (discount.isPresent()) {
	            double discountPercent = discount.get().getPercentageOfDiscount();
	            double finalPrice = Math.round(base * (1 - discountPercent / 100.0) * 100.0) / 100.0;

	            return new PricePointDto(
	                p.getDate(),
	                base,
	                finalPrice,
	                discountPercent,
	                true,
	                p.getStore(),
	                p.getBrand()
	            );
	        } else {
	            return new PricePointDto(
	                p.getDate(),
	                base,
	                null,
	                null,
	                false,
	                p.getStore(),
	                p.getBrand()
	            );
	        }
	    }).toList();
	}
}
