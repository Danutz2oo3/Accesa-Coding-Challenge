package com.accesa.pricecomparator.dto.discount;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewDiscountDto {
    private String productId;
    private String productName;
    private String store;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double discountPercent;
}

