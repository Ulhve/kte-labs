package com.example.shop.schedule;

import com.example.shop.dto.ProductWithDiscount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DiscountingProductEvent extends ApplicationEvent {

    private final ProductWithDiscount productDiscount;

    public DiscountingProductEvent(Object source, long productId, int discount) {
        super(source);
        this.productDiscount = new ProductWithDiscount(productId, discount);
    }
}
