package org.example.checkout;

import org.example.cart.Cart;
import org.example.product.ProductFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceImplTest {

    CheckoutService checkoutService = new CheckoutServiceImpl(
            new ProductFactory(
                Map.of(
                    'A', 50,
                    'B', 30,
                    'C', 20,
                    'D', 15)
    ));
    @Test
    void shouldCalculateTotalOfEmptyCart(){
        Cart emptyCart = new Cart(List.of());
        var total = checkoutService.calculateTotal(emptyCart);
        int paymentForEmptyCart = 0;
        assertEquals(paymentForEmptyCart, total);
    }



}