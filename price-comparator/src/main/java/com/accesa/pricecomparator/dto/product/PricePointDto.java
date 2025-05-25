package com.accesa.pricecomparator.dto.product;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricePointDto {
    private LocalDate date;
    private double basePrice;
    private Double finalPrice; // nullable: only if discounted
    private Double discountPercent; // nullable: only if discounted
    private boolean isDiscounted;
    private String store;
    private String brand;
}