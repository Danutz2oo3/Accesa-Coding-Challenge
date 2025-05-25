package com.accesa.pricecomparator.service.pricealert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.dto.PriceAlert;
import com.accesa.pricecomparator.dto.product.PricePointDto;
import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceAlertServiceImpl implements PriceAlertService {

	private final ProductPriceRepository priceRepo;
    private final DiscountRepository discountRepo;
    
    private final List<PriceAlert> alerts = new ArrayList<>();
	
	@Override
	public void registerAlert(String productId, double targetPrice) {
		alerts.add(new PriceAlert(productId, targetPrice));
	}

	@Override
	public List<PricePointDto> checkAlerts(LocalDate date) {
		List<PricePointDto> triggered = new ArrayList<>();

        for (PriceAlert alert : alerts) {
            List<ProductPrice> prices = priceRepo.findByProductIdAndDate(alert.getProductId(), date);

            for (ProductPrice price : prices) {
                double basePrice = price.getPrice();
                double finalPrice = basePrice;

                Optional<Discount> discount = discountRepo.findByProductIdAndStoreAndFromDateLessThanEqualAndToDateGreaterThanEqual(
                        price.getProductId(), price.getStore(), date, date);

                if (discount.isPresent()) {
                    double percent = discount.get().getPercentageOfDiscount();
                    finalPrice = Math.round(basePrice * (1 - percent / 100.0) * 100.0) / 100.0;
                }

                if (finalPrice <= alert.getTargetPrice()) {
                    triggered.add(new PricePointDto(
                            price.getDate(),
                            basePrice,
                            finalPrice,
                            discount.map(Discount::getPercentageOfDiscount).orElse(null),
                            true,
                            price.getStore(),
                            price.getBrand()
                    ));
                }
            }
        }

        return triggered;
    }
}
