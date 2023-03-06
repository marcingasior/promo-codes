package org.example.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Character> products;

    public Cart(List<Character> products) {
        this.products = new ArrayList<>(products);
    }
    
}
