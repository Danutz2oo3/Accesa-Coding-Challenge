package com.accesa.pricecomparator.service.discount;

import java.time.LocalDate;
import java.util.List;

import com.accesa.pricecomparator.dto.discount.NewDiscountDto;
import com.accesa.pricecomparator.dto.discount.TopDiscountDto;

public interface DiscountService {
	
	public List<TopDiscountDto> getTopDiscounts(LocalDate date, int limit);
	
	public List<NewDiscountDto> getRecentDiscounts(LocalDate date);
	
}
