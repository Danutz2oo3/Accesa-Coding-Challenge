package com.accesa.pricecomparator.service.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.dto.basket.OptimizeBasketResponse;
import com.accesa.pricecomparator.dto.basket.OptimizedItemDto;
import com.accesa.pricecomparator.dto.product.SubstituteProductDto;
import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
	
	private final ProductPriceRepository productPriceRepository;
	private final DiscountRepository discountRepository;
	
	@Override
	public OptimizeBasketResponse optimizeBasket(List<String> productIds, LocalDate date) {
		List<OptimizedItemDto> results = new ArrayList<>();
	    double total = 0.0;

	    for (String productId : productIds) {
	        List<ProductPrice> entries = productPriceRepository.findByProductIdAndDate(productId, date);
	        if (entries.isEmpty()) continue;

	        Optional<OptimizedItemDto> best = entries.stream().map(p -> {
	            Optional<Discount> discount = discountRepository.findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
	                    productId, p.getStore(), date, date);
	            double base = p.getPrice();
	            double percent = discount.map(Discount::getPercentageOfDiscount).orElse(0.0);
	            double discountedPrice = base * (1 - percent / 100.0);
	            double finalPrice = BigDecimal.valueOf(discountedPrice)
	            	    .setScale(2, RoundingMode.HALF_UP)
	            	    .doubleValue();
	            return new OptimizedItemDto(
	                    p.getProductId(), p.getProductName(), p.getStore(),
	                    base, finalPrice, percent, percent > 0);
	        }).min(Comparator.comparingDouble(OptimizedItemDto::finalPrice));

	        if (best.isPresent()) {
	            results.add(best.get());
	            total += best.get().finalPrice();
	        }
	    }

	    return new OptimizeBasketResponse(total, results);
	}
	
	@Override
	public List<SubstituteProductDto> getSubstitutes(String productId, LocalDate date) {
	    List<ProductPrice> entries = productPriceRepository.findByProductIdAndDate(productId, date);

	    if (entries.isEmpty()) {
	        throw new RuntimeException("No products found for productId " + productId + " on date " + date);
	    }

	    return entries.stream().map(p -> {
	        double basePrice = p.getPrice();
	        double quantity = p.getPackageQuantity();
	        String unit = p.getPackageUnit();

	        Optional<Discount> discount = discountRepository.findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
	                p.getProductId(), p.getStore(), date, date);

	        double finalPrice = basePrice;
	        Double discountPercent = null;
	        boolean isDiscounted = false;

	        if (discount.isPresent()) {
	            discountPercent = discount.get().getPercentageOfDiscount();
	            finalPrice = Math.round(basePrice * (1 - discountPercent / 100.0) * 100.0) / 100.0;
	            isDiscounted = true;
	        }

	        double valuePerUnit = quantity > 0
	                ? Math.round((finalPrice / quantity) * 100.0) / 100.0
	                : 0.0;

	        return new SubstituteProductDto(
	                p.getProductId(),
	                p.getProductName(),
	                p.getBrand(),
	                p.getStore(),
	                basePrice,
	                discountPercent,
	                isDiscounted ? finalPrice : null,
	                isDiscounted,
	                quantity,
	                unit,
	                valuePerUnit
	        );
	    }).sorted(Comparator.comparingDouble(SubstituteProductDto::getValuePerUnit))
	      .toList();
	}

}
