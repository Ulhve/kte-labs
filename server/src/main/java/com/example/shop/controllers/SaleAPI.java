package com.example.shop.controllers;

import com.example.shop.controllers.request.SaleRequest;
import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.Statistics;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SaleAPI {
    Long calculate(Long clientId, List<ProductWithCount> products);
    Statistics statistics(@Valid @RequestBody ClientWithProduct request);
    String order(Long clientId, SaleRequest request);
}
