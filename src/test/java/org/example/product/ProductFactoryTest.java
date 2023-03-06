package org.example.product;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductFactoryTest {


    private static ProductFactory aFactory() {
        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', 50);
        return new ProductFactory(mapping);
    }

    @Test
    void shouldReturnInstance() {
        var productOpt = aFactory().getInstance('A');
        assertTrue(productOpt.isPresent());
        assertEquals('A', productOpt.get().name());
        assertEquals(50, productOpt.get().price());
    }

    @Test
    void shouldReturnEmptyOption() {
        var productOpt = aFactory().getInstance('B');
        assertTrue(productOpt.isEmpty());
    }
}