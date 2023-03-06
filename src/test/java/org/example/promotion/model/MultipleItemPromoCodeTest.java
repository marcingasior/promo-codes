package org.example.promotion.model;

import org.example.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleItemPromoCodeTest {

    PromoCode promoCode;
    @BeforeEach
    void setUp() {
        Product a = new Product('A', 50);
        promoCode = new MultipleItemPromoCode(a,3, 130);
    }
    @Test
    void calculateDiscount() {
        assertEquals(-20, promoCode.calculateDiscount());

    }
}