package com.accesa.pricecomparator.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubstituteProductDto {
    private String productId;
    private String productName;
    private String brand;
    private String store;
    private double basePrice;
    private Double discountPercent;
    private Double finalPrice;
    private boolean isDiscounted;
    private double packageQuantity;
    private String packageUnit;
    private double valuePerUnit;

}