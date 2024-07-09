package com.czareg.basket.promotions;

import org.joda.money.Money;

import java.util.List;

import static org.joda.money.CurrencyUnit.EUR;

public class PromotionConfigurator {

    public List<Promotion> getPromotionConfiguration() {
        return List.of(
                new BigBasketPromotion(40, Money.of(EUR, 5)),
                new ExpensiveBasketPromotion(
                        Money.of(EUR, 100),
                        Money.of(EUR, 10))
        );
    }
}
