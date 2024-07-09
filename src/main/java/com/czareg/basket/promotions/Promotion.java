package com.czareg.basket.promotions;

import com.czareg.basket.Basket;
import org.joda.money.Money;

public interface Promotion {

    boolean isApplicable(Basket basket);

    Money calculateDiscount(Basket basket);
}
