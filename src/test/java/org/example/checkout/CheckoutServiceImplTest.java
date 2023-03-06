package org.example.checkout;

import org.example.cart.Cart;
import org.example.product.Product;
import org.example.product.ProductFactory;
import org.example.promotion.model.ItemSetPromoCode;
import org.example.promotion.model.MultiPackPromoCode;
import org.example.promotion.service.PromotionEngine;
import org.example.promotion.service.PromotionEngineImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutServiceImplTest {

    CheckoutService checkoutService = new CheckoutServiceImpl(
            getProductFactory(),
            getPromotionEngine()
    );

    private static PromotionEngine getPromotionEngine() {
        return new PromotionEngineImpl(List.of(
                new MultiPackPromoCode(aProduct('A'), 3, 130),
                new MultiPackPromoCode(aProduct('B'), 2, 45),
                new ItemSetPromoCode(Set.of(aProduct('C'), aProduct('D')), 30))
        );
    }

    private static Product aProduct(Character c) {
        return getProductFactory().getInstance(c).get();
    }

    private static ProductFactory getProductFactory() {
        return new ProductFactory(
                Map.of(
                        'A', 50,
                        'B', 30,
                        'C', 20,
                        'D', 15)
        );
    }

    private static Stream<Character> createLetterStream(int endIndx, Character letter) {
        return IntStream.range(0, endIndx).mapToObj(i -> letter);
    }

    @Test
    void shouldCalculateTotalOfEmptyCart() {
        Cart emptyCart = new Cart(List.of());
        var total = checkoutService.calculateTotal(emptyCart);
        int paymentForEmptyCart = 0;
        assertEquals(paymentForEmptyCart, total);
    }

    @Test
    void shouldCalculateCartOfDistinctItems() {
        Cart cart = new Cart(List.of('A', 'B', 'C', 'D'));
        var total = checkoutService.calculateTotal(cart);
        int paymentForCart = 110;
        assertEquals(paymentForCart, total);
    }

    @Test
    void shouldAddDiscountForMultipleItems() {
        Cart cart = new Cart(createLetterStream(4, 'A').toList());
        var total = checkoutService.calculateTotal(cart);
        int paymentForCart = 180;
        assertEquals(paymentForCart, total);
    }

    @Test
    void shouldPassTestScenarioA() {

        Cart cart = new Cart(List.of('A', 'B', 'C'));
        var total = checkoutService.calculateTotal(cart);
        int paymentForCart = 100;
        assertEquals(paymentForCart, total);
    }

    @Test
    void shouldPassTestScenarioB() {
        var aStream = createLetterStream(5, 'A');
        var bStream = createLetterStream(5, 'B');
        var cStream = Stream.of('C');

        List<Character> products = Stream.concat(Stream.concat(aStream, bStream), cStream).toList();
        Cart cart = new Cart(products);
        var total = checkoutService.calculateTotal(cart);
        int paymentForCart = 370;
        assertEquals(paymentForCart, total);
    }

    @Test
    void shouldPassTestScenarioC() {
        var aStream = createLetterStream(3, 'A');
        var bStream = createLetterStream(5, 'B');
        var otherStream = Stream.of('C', 'D');

        List<Character> products = Stream.concat(Stream.concat(aStream, bStream), otherStream).toList();
        Cart cart = new Cart(products);
        var total = checkoutService.calculateTotal(cart);
        int paymentForCart = 280;
        assertEquals(paymentForCart, total);

    }

}