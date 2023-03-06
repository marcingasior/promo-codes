package org.example.promotion.service;

import org.example.cart.Cart;
import org.example.promotion.model.PromoCode;

import java.util.List;
import java.util.Optional;

public class PromotionEngineImpl implements PromotionEngine {

    private final List<PromoCode> promoCodeList;

    public PromotionEngineImpl(List<PromoCode> promoCodeList){
        this.promoCodeList = promoCodeList;
    }

    @Override
    public int getDiscount(Cart cart) {

        var promoCodeOpt = findApplicablePromoCode(cart);
        return promoCodeOpt.map(pc -> pc.calculateDiscount(cart)).orElse(0);
    }


    protected Optional<PromoCode> findApplicablePromoCode(Cart cart) {

        return promoCodeList.stream().filter(promoCode -> promoCode.isApplicable(cart)).findFirst();
    }
}
