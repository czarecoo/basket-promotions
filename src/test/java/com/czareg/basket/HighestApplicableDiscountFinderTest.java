package com.czareg.basket;

import com.czareg.basket.promotions.PromotionConfigurator;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;

class HighestApplicableDiscountFinderTest {

    PromotionConfigurator promotionConfigurator = new PromotionConfigurator();
    HighestApplicableDiscountFinder discountFinder = new HighestApplicableDiscountFinder(promotionConfigurator);

    @Test
    void shouldReturnHighestDiscountWhenBothPromotionsAreApplicable() {
        Product product = new Product("test", Money.of(EUR, BigDecimal.ONE));
        Map<Product, Integer> productQuantity = Map.of(product, 150);
        Basket basket = new Basket(productQuantity);

        Money result = discountFinder.findDiscount(basket);

        assertThat(result).isEqualTo(Money.of(EUR, 10));
    }

    @Test
    void shouldReturnBigBasketDiscountWhenOnlyBigBasketPromotionIsApplicable() {
        Product product = new Product("test", Money.of(EUR, BigDecimal.valueOf(1)));
        Map<Product, Integer> productQuantity = Map.of(product, 50);
        Basket basket = new Basket(productQuantity);

        Money result = discountFinder.findDiscount(basket);

        assertThat(result).isEqualTo(Money.of(EUR, 5));
    }

    @Test
    void shouldReturnExpensiveBasketDiscountWhenOnlyExpensiveBasketPromotionIsApplicable() {
        Product product = new Product("test", Money.of(EUR, BigDecimal.valueOf(5)));
        Map<Product, Integer> productQuantity = Map.of(product, 30);
        Basket basket = new Basket(productQuantity);

        Money result = discountFinder.findDiscount(basket);

        assertThat(result).isEqualTo(Money.of(EUR, 10));
    }

    @Test
    void shouldReturnZeroWhenNoPromotionsAreApplicable() {
        Product product = new Product("test", Money.of(EUR, BigDecimal.valueOf(1)));
        Map<Product, Integer> productQuantity = Map.of(product, 10);
        Basket basket = new Basket(productQuantity);

        Money result = discountFinder.findDiscount(basket);

        assertThat(result).isEqualTo(Money.of(EUR, 0));
    }
}