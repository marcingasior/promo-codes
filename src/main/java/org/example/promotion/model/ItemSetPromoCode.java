package org.example.promotion.model;

import org.example.cart.Cart;
import org.example.product.Product;

import java.util.List;

public class ItemSetPromoCode implements PromoCode{

    private final List<Product> products;
    private final int discountPrice;


    public ItemSetPromoCode(List<Product> products, int discountPrice){
        this.products = products;
        this.discountPrice = discountPrice;
    }

    @Override
    public int calculateDiscount(Cart cart) {
        return 0;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        return false;
    }
}
