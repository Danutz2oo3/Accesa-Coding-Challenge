package com.accesa.pricecomparator.controller.discount;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.discount.NewDiscountDto;
import com.accesa.pricecomparator.dto.discount.TopDiscountDto;
import com.accesa.pricecomparator.service.discount.DiscountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DiscountControllerImpl implements DiscountController {

	 private final DiscountService discountService;
	
	@Override
	public ResponseEntity<List<TopDiscountDto>> topDiscounts(String date, int limit) {
		LocalDate parsed = LocalDate.parse(date);
        return ResponseEntity.ok(discountService.getTopDiscounts(parsed, limit));
	}

	@Override
	public ResponseEntity<List<NewDiscountDto>> getNewDiscounts(String date) {
		LocalDate parsed = LocalDate.parse(date);
	    return ResponseEntity.ok(discountService.getRecentDiscounts(parsed));
	}

}
