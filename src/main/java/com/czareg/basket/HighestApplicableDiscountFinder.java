package com.czareg.basket;

import com.czareg.basket.promotions.Promotion;
import com.czareg.basket.promotions.PromotionConfigurator;
import lombok.RequiredArgsConstructor;
import org.joda.money.Money;

import java.util.List;

@RequiredArgsConstructor
public class HighestApplicableDiscountFinder {

    private final PromotionConfigurator promotionConfigurator;

    public Money findDiscount(Basket basket) {
        List<Promotion> promotions = promotionConfigurator.getPromotionConfiguration();
        return promotions.stream()
                .filter(promotion -> promotion.isApplicable(basket))
                .map(promotion -> promotion.calculateDiscount(basket))
                .max(Money::compareTo)
                .orElse(promotionConfigurator.getNoPromotion());
    }
}
