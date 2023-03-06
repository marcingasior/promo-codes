package org.example.checkout;

import org.example.cart.Cart;

public interface CheckoutService {

    Integer calculateTotal(Cart cart);

}
