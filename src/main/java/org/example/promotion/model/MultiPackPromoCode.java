package org.example.promotion.model;

import org.example.cart.Cart;
import org.example.product.Product;

import java.util.Objects;

public class MultiPackPromoCode implements PromoCode {

    private final Product product;
    private final int count;

    private final int discountPrice;

    public MultiPackPromoCode(Product product, int count, int discountPrice) {
        Objects.requireNonNull(product);
        if (count < 1) throw new IllegalArgumentException("Count can't be less than 1");
        if (discountPrice < 1) throw new IllegalArgumentException("Discount price can't be less than 1");

        this.product = product;
        this.count = count;
        this.discountPrice = discountPrice;

    }


    @Override
    public int calculateDiscount(Cart cart) {
        var applicability = applicabilityCount(cart);
        return (this.discountPrice - product.price() * count) * applicability;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return groupProductsInCart(cart)
                .getOrDefault(product.name(), 0L).intValue() >= count;
    }

    public int applicabilityCount(Cart cart) {
        return groupProductsInCart(cart)
                .getOrDefault(product.name(), 0L).intValue() / count;
    }
}
