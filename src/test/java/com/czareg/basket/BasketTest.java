package com.czareg.basket;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @Test
    void shouldCorrectlyCalculateBasketTotalPriceWhenGivenOneProduct() {
        Product product = new Product("test", Money.of(CurrencyUnit.EUR, BigDecimal.ONE));
        Map<Product, Integer> productQuantity = Map.of(product, 5);
        Basket basket = new Basket(productQuantity);

        BigDecimal result = basket.calculateTotalPrice();

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(5));
    }

    @Test
    void shouldCorrectlyCalculateBasketTotalPriceWhenGivenTwoProducts() {
        Product productOne = new Product("test1", Money.of(CurrencyUnit.EUR, BigDecimal.ONE));
        Product productTwo = new Product("test2", Money.of(CurrencyUnit.EUR, BigDecimal.TEN));
        Map<Product, Integer> productQuantity = Map.of(productOne, 5, productTwo, 10);
        Basket basket = new Basket(productQuantity);

        BigDecimal result = basket.calculateTotalPrice();

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(105));
    }

    @Test
    void shouldCorrectlyCountItemsWhenGivenTwoItems() {
        Product productOne = new Product("test1", Money.of(CurrencyUnit.EUR, BigDecimal.ONE));
        Product productTwo = new Product("test2", Money.of(CurrencyUnit.EUR, BigDecimal.TEN));
        Map<Product, Integer> productQuantity = Map.of(productOne, 5, productTwo, 10);
        Basket basket = new Basket(productQuantity);

        int itemCount = basket.countItems();

        assertEquals(15, itemCount);
    }

    @Test
    void shouldCorrectlyCountItemsForEmptyBasket() {
        Map<Product, Integer> productQuantity = Map.of();
        Basket basket = new Basket(productQuantity);

        int itemCount = basket.countItems();

        assertEquals(0, itemCount);
    }
}