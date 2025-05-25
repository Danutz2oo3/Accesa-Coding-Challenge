package com.accesa.pricecomparator.dto.basket;

import java.util.List;

public record OptimizeBasketResponse(
    double total,
    List<OptimizedItemDto> optimizedBasket
) {}
