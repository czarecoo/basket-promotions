package com.czareg.basket;

import org.joda.money.Money;

public record Product(String name, Money price) {
}
