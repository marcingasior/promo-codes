package org.example.product;

import java.util.Map;
import java.util.Optional;

public class ProductFactory {
    private final Map<Character, Integer> idToProductMapping;

    public ProductFactory(Map<Character, Integer> idToProductMapping) {
        this.idToProductMapping = idToProductMapping;
    }

    public Optional<Product> getInstance(Character id) {
        if (idToProductMapping.containsKey(id)) {
            return Optional.of(new Product(id, idToProductMapping.get(id)));
        }
        return Optional.empty();
    }
}
