package org.example.promotion.model;

import org.example.product.Product;

import java.util.List;
import java.util.Map;

public interface PromoCode {

    int calculateDiscount();

    boolean isApplicable(Map<Character, Long> productCount);
}
