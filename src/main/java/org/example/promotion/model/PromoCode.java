package org.example.promotion.model;

import org.example.cart.Cart;

import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public interface PromoCode {

    int calculateDiscount(Cart cart);

    boolean isApplicable(Cart cart);

    default Map<Character, Long> groupProductsInCart(Cart cart){
        return cart.products().stream().collect(groupingBy(Function.identity(), counting()));
    }
}
