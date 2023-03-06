package org.example.promotion.model;

import org.example.cart.Cart;
import org.example.product.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemSetPromoCodeTest {


    ItemSetPromoCode promoCode = new ItemSetPromoCode(
            Set.of(
                    Product.of('C', 20),
                    Product.of('D', 15)
            ), 30);

    Cart cart = new Cart(List.of('C', 'D'));

    @Test
    void calculateDiscount() {
        var discount = promoCode.calculateDiscount(cart);
        var expectedDiscount = -5;
        assertEquals(expectedDiscount, discount);
    }

    @Test
    void isApplicable() {
        var result = promoCode.isApplicable(cart);
        assertTrue(result);
    }

    @Test
    void isNotApplicable() {
        Cart cart = new Cart(List.of('C', 'B'));
        var result = promoCode.isApplicable(cart);
        assertFalse(result);
    }
}