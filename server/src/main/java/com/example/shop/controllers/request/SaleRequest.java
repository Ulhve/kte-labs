package com.example.shop.controllers.request;

import com.example.shop.dto.ProductWithCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {

    private List<ProductWithCount> productsWithCounts;
    private Long totalCost;

}
