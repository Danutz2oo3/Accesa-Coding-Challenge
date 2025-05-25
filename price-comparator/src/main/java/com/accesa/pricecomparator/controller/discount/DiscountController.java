package com.accesa.pricecomparator.controller.discount;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accesa.pricecomparator.dto.discount.NewDiscountDto;
import com.accesa.pricecomparator.dto.discount.TopDiscountDto;

@RequestMapping("/api/discounts")
public interface DiscountController {
	
	@GetMapping("/top")
    public ResponseEntity<List<TopDiscountDto>> topDiscounts(
            @RequestParam String date,
            @RequestParam(defaultValue = "10") int limit);
	
	@GetMapping("/new")
	public ResponseEntity<List<NewDiscountDto>> getNewDiscounts(@RequestParam String date);
	
}
