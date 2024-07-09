package com.czareg.basket.promotions;

import com.czareg.basket.Basket;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BigBasketPromotionTest {

    @Test
    void shouldApplyPromotionWhenItemsInBasketExceedRequiredAmount() {
        Basket basket = mock(Basket.class);
        when(basket.countItems()).thenReturn(10);
        Promotion promotion = new BigBasketPromotion(5, Money.of(EUR, BigDecimal.TEN));

        boolean result = promotion.isApplicable(basket);

        assertTrue(result);
    }

    @Test
    void shouldNotApplyPromotionWhenItemsInBasketDoNotExceedRequiredAmount() {
        Basket basket = mock(Basket.class);
        when(basket.countItems()).thenReturn(1);
        Promotion promotion = new BigBasketPromotion(5, Money.of(EUR, BigDecimal.TEN));

        boolean result = promotion.isApplicable(basket);

        assertFalse(result);
    }

    @Test
    void shouldCalculateCorrectDiscount() {
        Promotion promotion = new BigBasketPromotion(5, Money.of(EUR, BigDecimal.TEN));

        Money result = promotion.calculateDiscount(new Basket(Map.of()));

        assertThat(result).isEqualTo(Money.of(EUR, BigDecimal.TEN));
    }
}