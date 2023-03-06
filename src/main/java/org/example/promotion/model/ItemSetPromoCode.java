package org.example.promotion.model;

import org.example.cart.Cart;
import org.example.product.Product;

import java.util.Map;
import java.util.Set;

public class ItemSetPromoCode implements PromoCode {

    private final Set<Product> products;
    private final int discountPrice;


    public ItemSetPromoCode(Set<Product> products, int discountPrice) {
        this.products = products;
        this.discountPrice = discountPrice;
    }

    @Override
    public int calculateDiscount(Cart cart) {
        return this.discountPrice - products.stream().map(Product::price).reduce(Integer::sum).orElse(0);
    }

    @Override
    public boolean isApplicable(Cart cart) {
        Map<Character, Long> groupProductsInCart = groupProductsInCart(cart);

        //TODO change to discover multiple sets
        return products.stream().allMatch(p -> groupProductsInCart.containsKey(p.name()));
    }
}
