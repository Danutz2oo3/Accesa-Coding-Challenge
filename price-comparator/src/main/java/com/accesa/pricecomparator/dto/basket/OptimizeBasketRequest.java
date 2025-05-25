package com.accesa.pricecomparator.dto.basket;

import java.time.LocalDate;
import java.util.List;

public record OptimizeBasketRequest(List<String> productIds, LocalDate date) {}