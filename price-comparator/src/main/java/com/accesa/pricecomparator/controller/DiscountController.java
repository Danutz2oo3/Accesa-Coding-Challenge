package com.accesa.pricecomparator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.dto.NewDiscountDto;
import com.accesa.pricecomparator.dto.TopDiscountDto;

@RequestMapping("/api/discounts")
public interface DiscountController {
	
	@GetMapping("/top")
    public List<TopDiscountDto> topDiscounts(
            @RequestParam String date,
            @RequestParam(defaultValue = "10") int limit);
	
	@GetMapping("/new")
	public List<NewDiscountDto> getNewDiscounts(@RequestParam String date);
	
}
