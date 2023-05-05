package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithDiscount {
    private Long productId;
    private Integer percentDiscount;
}
