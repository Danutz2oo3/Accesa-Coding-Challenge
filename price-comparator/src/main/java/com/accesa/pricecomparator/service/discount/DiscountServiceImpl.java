package com.accesa.pricecomparator.service.discount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.dto.discount.NewDiscountDto;
import com.accesa.pricecomparator.dto.discount.TopDiscountDto;
import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
	
	private final DiscountRepository discountRepo;
    private final ProductPriceRepository priceRepo;
	
	@Override
	public List<TopDiscountDto> getTopDiscounts(LocalDate date, int limit) {
		List<Discount> activeDiscounts = discountRepo.findActiveDiscounts(date);

        List<TopDiscountDto> top = new ArrayList<>();

        for (Discount d : activeDiscounts) {
            Optional<ProductPrice> priceOpt = priceRepo.findFirstByProductIdAndStoreAndDate(
                d.getProductId(), d.getStore(), date
            );

            if (priceOpt.isPresent()) {
                double originalPrice = priceOpt.get().getPrice();
                double discount = d.getPercentageOfDiscount();
                double finalPrice = Math.round(originalPrice * (1 - discount / 100.0) * 100.0) / 100.0;

                top.add(new TopDiscountDto(
                    d.getProductId(),
                    d.getProductName(),
                    d.getBrand(),
                    d.getStore(),
                    originalPrice,
                    discount,
                    finalPrice
                ));
            }
        }

        return top.stream()
            .sorted(Comparator.comparingDouble(TopDiscountDto::getDiscountPercent).reversed())
            .limit(limit)
            .toList();
    }

	@Override
	public List<NewDiscountDto> getRecentDiscounts(LocalDate date) {
	    return discountRepo.findByFromDate(date).stream()
	        .map(d -> new NewDiscountDto(
	            d.getProductId(),
	            d.getProductName(),
	            d.getStore(),
	            d.getFromDate(),
	            d.getToDate(),
	            d.getPercentageOfDiscount()
	        ))
	        .toList();
	}

}
