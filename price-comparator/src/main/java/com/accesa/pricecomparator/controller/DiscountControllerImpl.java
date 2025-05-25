package com.accesa.pricecomparator.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.accesa.pricecomparator.dto.NewDiscountDto;
import com.accesa.pricecomparator.dto.TopDiscountDto;
import com.accesa.pricecomparator.service.DiscountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DiscountControllerImpl implements DiscountController {

	 private final DiscountService discountService;
	
	@Override
	public List<TopDiscountDto> topDiscounts(String date, int limit) {
		LocalDate parsed = LocalDate.parse(date);
        return discountService.getTopDiscounts(parsed, limit);
	}

	@Override
	public List<NewDiscountDto> getNewDiscounts(String date) {
		LocalDate parsed = LocalDate.parse(date);
	    return discountService.getRecentDiscounts(parsed);
	}

}
