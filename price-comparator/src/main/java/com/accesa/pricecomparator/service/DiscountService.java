package com.accesa.pricecomparator.service;

import java.time.LocalDate;
import java.util.List;

import com.accesa.pricecomparator.dto.NewDiscountDto;
import com.accesa.pricecomparator.dto.TopDiscountDto;

public interface DiscountService {
	
	public List<TopDiscountDto> getTopDiscounts(LocalDate date, int limit);
	
	public List<NewDiscountDto> getRecentDiscounts(LocalDate date);
	
}
