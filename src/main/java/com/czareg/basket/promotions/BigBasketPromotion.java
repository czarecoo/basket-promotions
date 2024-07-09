package com.czareg.basket.promotions;

import com.czareg.basket.Basket;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

@RequiredArgsConstructor
public class BigBasketPromotion implements Promotion {

    private final int itemsRequired;
    private final Money discount;

    @Override
    public boolean isApplicable(Basket basket) {
        return basket.countItems() >= itemsRequired;
    }

    @Override
    public Money calculateDiscount(Basket basket) {
        return discount;
    }
}
