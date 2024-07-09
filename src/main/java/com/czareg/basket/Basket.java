package com.czareg.basket;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class Basket {

    private final Map<Product, Integer> productsByQuantity;

    public int countItems() {
        return productsByQuantity.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public BigDecimal calculateTotalPrice() {
        return productsByQuantity.entrySet()
                .stream()
                .map(Basket::calculatePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal calculatePrice(Map.Entry<Product, Integer> entry) {
        BigDecimal price = entry.getKey().price().getAmount();
        BigDecimal quantity = new BigDecimal(entry.getValue());
        return price.multiply(quantity);
    }
}
