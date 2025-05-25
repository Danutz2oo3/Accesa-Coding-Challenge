package com.accesa.pricecomparator.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.dto.ShoppingBasketResponse;
import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
	
	private final ProductPriceRepository productRepo;
	private final DiscountRepository discountRepo;
	
	@Override
	public ShoppingBasketResponse evaluateBasket(List<String> productIds, LocalDate date) {
		Map<String, Map<String, Double>> storeItemPrices = new HashMap<>();
        Map<String, Double> storeTotals = new HashMap<>();

        for (String pid : productIds) {
            List<ProductPrice> prices = productRepo.findByProductIdAndDate(pid, date);

            for (ProductPrice price : prices) {
                double originalPrice = price.getPrice();

                // Apply discount if found
                Optional<Discount> discountOpt = discountRepo.findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
                        price.getProductId(), price.getStore(), date, date);

                double discounted = discountOpt
                	    .map(d -> originalPrice * (1 - d.getPercentageOfDiscount() / 100.0))
                	    .orElse(originalPrice);

                double finalPrice = Math.round(discounted * 100.0) / 100.0;

                // Save item price per store
                storeItemPrices
                    .computeIfAbsent(price.getStore(), k -> new HashMap<>())
                    .put(pid, finalPrice);

                // Accumulate total
                storeTotals.merge(price.getStore(), finalPrice, Double::sum);
            }
        }

        // Find best store
        String bestStore = storeTotals.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);

        ShoppingBasketResponse response = new ShoppingBasketResponse();
        response.setBestStore(bestStore);
        response.setBestTotal(storeTotals.getOrDefault(bestStore, 0.0));
        response.setTotalPerStore(storeTotals);
        response.setItemPricesPerStore(storeItemPrices);

        return response;
	}

}
