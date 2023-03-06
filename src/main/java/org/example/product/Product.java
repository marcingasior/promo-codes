package org.example.product;

public record Product(Character name, Integer price) {

    public Product(Character name, Integer price){
        if (name == null || price == null || price < 0) throw new IllegalArgumentException();

        this.price = price;
        this.name = name;
    }
    public static Product of(Character name, Integer price){
        return new Product(name, price);
    }
}
