package org.example.checkout;

import org.example.cart.Cart;
import org.example.product.Product;
import org.example.product.ProductFactory;
import org.example.promotion.service.PromotionEngine;

import java.util.Optional;

public class CheckoutServiceImpl implements CheckoutService {

    private final ProductFactory productFactory;
    private final PromotionEngine promotionEngine;

    public CheckoutServiceImpl(ProductFactory productFactory, PromotionEngine promotionEngine) {
        this.productFactory = productFactory;
        this.promotionEngine = promotionEngine;
    }

    @Override
    public Integer calculateTotal(Cart cart) {
        Integer total = cart.products()
                .stream()
                .map(productFactory::getInstance)
                .flatMap(Optional::stream)
                .map(Product::price)
                .reduce(Integer::sum).orElse(0);

        Integer discount = promotionEngine.getDiscount(cart);

        return total + discount;
    }
}
