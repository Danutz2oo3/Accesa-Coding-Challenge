package com.accesa.pricecomparator.dto;

import java.util.Map;

import lombok.Data;

@Data
public class ShoppingBasketResponse {
    private String bestStore;
    private double bestTotal;
    private Map<String, Double> totalPerStore;
    private Map<String, Map<String, Double>> itemPricesPerStore;
}
