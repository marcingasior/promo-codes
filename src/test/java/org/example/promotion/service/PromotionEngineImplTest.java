package org.example.promotion.service;

import org.example.cart.Cart;
import org.example.product.Product;
import org.example.promotion.model.MultiPackPromoCode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionEngineImplTest {

    MultiPackPromoCode ax3Promo = new MultiPackPromoCode(new Product('A', 50), 3, 130);
    MultiPackPromoCode bx2Promo = new MultiPackPromoCode(new Product('B', 30), 2, 45);
    PromotionEngineImpl promotionEngine = new PromotionEngineImpl(
            List.of(
                    ax3Promo,
                    bx2Promo
            ));


    @Test
    void getDiscount() {
    }

    @Test
    void findApplicablePromoCode() {
        int discount = promotionEngine.getDiscount(new Cart(List.of('A', 'A', 'A')));

        assertEquals(-20, discount);
    }

    @Test
    void applyPromoCodeMultipleTimes() {
        int discount = promotionEngine.getDiscount(new Cart(List.of('B', 'B', 'B', 'B')));
        int expectedDiscount = -30;

        assertEquals(expectedDiscount, discount);

    }
}