package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    private Integer countChecks;
    private BigDecimal totalCost;
    private BigDecimal totalDiscount;

}
