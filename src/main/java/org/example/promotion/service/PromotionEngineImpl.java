package org.example.promotion.service;

import org.example.cart.Cart;
import org.example.promotion.model.PromoCode;

import java.util.List;
import java.util.stream.Stream;

public class PromotionEngineImpl implements PromotionEngine {

    private final List<PromoCode> promoCodeList;

    public PromotionEngineImpl(List<PromoCode> promoCodeList) {
        this.promoCodeList = promoCodeList;
    }

    @Override
    public int getDiscount(Cart cart) {
        var promoCodeStream = findApplicablePromoCode(cart);
        return promoCodeStream.map(pc -> pc.calculateDiscount(cart)).reduce(Integer::sum).orElse(0);
    }


    protected Stream<PromoCode> findApplicablePromoCode(Cart cart) {

        return promoCodeList.stream().filter(promoCode -> promoCode.isApplicable(cart));
    }
}
