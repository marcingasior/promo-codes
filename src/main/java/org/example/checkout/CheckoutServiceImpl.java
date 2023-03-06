package org.example.checkout;

import org.example.cart.Cart;
import org.example.product.Product;
import org.example.product.ProductFactory;

import java.util.Optional;
import java.util.function.BinaryOperator;

import static java.util.function.Function.identity;

public class CheckoutServiceImpl implements CheckoutService{

    private ProductFactory productFactory;

    public CheckoutServiceImpl(ProductFactory productFactory){
        this.productFactory = productFactory;
    }
    @Override
    public Integer calculateTotal(Cart cart) {
        Integer total = cart.products()
                .stream()
                .map(productFactory::getInstance)
                .flatMap(Optional::stream)
                .map(Product::price)
                .reduce((a,b) -> a+b).orElse(0);
        return total;
    }
}
