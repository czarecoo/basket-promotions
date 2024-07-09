package com.czareg.basket.promotions;

import com.czareg.basket.Basket;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

@RequiredArgsConstructor
public class ExpensiveBasketPromotion implements Promotion {

    private final Money valueRequired;
    private final Money discount;

    @Override
    public boolean isApplicable(Basket basket) {
        return basket.calculateTotalPrice().compareTo(valueRequired.getAmount()) >= 0;
    }

    @Override
    public Money calculateDiscount(Basket basket) {
        return discount;
    }
}
