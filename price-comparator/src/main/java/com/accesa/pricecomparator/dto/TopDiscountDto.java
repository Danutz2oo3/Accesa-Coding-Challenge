package com.accesa.pricecomparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopDiscountDto {
    private String productId;
    private String productName;
    private String brand;
    private String store;
    private double originalPrice;
    private double discountPercent;
    private double finalPrice;
}
