package org.example.product;

public record Product(Character name, Integer price) {

    public Product {
        if (name == null || price == null || price < 0) throw new IllegalArgumentException();

    }

    public static Product of(Character name, Integer price) {
        return new Product(name, price);
    }
}
