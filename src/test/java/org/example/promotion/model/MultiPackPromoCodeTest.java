package org.example.promotion.model;

import org.example.cart.Cart;
import org.example.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MultiPackPromoCodeTest {

    PromoCode promoCode;

    @BeforeEach
    void setUp() {
        Product a = new Product('A', 50);
        promoCode = new MultiPackPromoCode(a,3, 130);
    }
    @Test
    void calculateDiscount() {
        Cart cart = new Cart(List.of('A','A','A'));
        assertEquals(-20, promoCode.calculateDiscount(cart));
    }

    @Test
    void calculateDiscountAppliedTwice() {
        List<Character> products = IntStream.range(0,6).mapToObj(i->'A').collect(Collectors.toList());
        Cart cart = new Cart(products);
        assertEquals(-40, promoCode.calculateDiscount(cart));
    }
}