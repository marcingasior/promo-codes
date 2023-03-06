package org.example.promotion.model;

import org.example.product.Product;

import java.util.Map;
import java.util.Objects;

public class MultipleItemPromoCode implements PromoCode {

    private final Product product;
    private final int count;

    private final int discountPrice;

    public MultipleItemPromoCode(Product product, int count, int discountPrice) {
        Objects.requireNonNull(product);
        if (count < 1) throw new IllegalArgumentException("Count can't be less than 1");
        if (discountPrice < 1) throw new IllegalArgumentException("Discount price can't be less than 1");

        this.product = product;
        this.count = count;
        this.discountPrice = discountPrice;

    }


    @Override
    public int calculateDiscount() {
        return this.discountPrice - product.price() * count;
    }

    @Override
    public boolean isApplicable(Map<Character, Long> productCount) {
        return productCount.getOrDefault(product.name(), 0L).intValue() >= count;
    }
}
