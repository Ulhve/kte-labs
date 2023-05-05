package com.example.shop.controllers.rest;

import com.example.shop.controllers.SaleAPI;
import com.example.shop.controllers.request.SaleRequest;
import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.Statistics;
import com.example.shop.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/sale")
@RequiredArgsConstructor
public class SaleController implements SaleAPI {

    private final SaleService saleService;

    @PutMapping("/{clientId}/calculate")
    @Override
    public Long calculate(
            @PathVariable Long clientId,
            @Valid @RequestBody List<ProductWithCount> products) {
        return saleService.calculateFinalPrice(clientId, products);
    }

    @PutMapping("/statistic")
    @Override
    public Statistics statistics(@Valid @RequestBody ClientWithProduct request) {
        return saleService.getStatistics(request);
    }

    @PostMapping("/{clientId}/order")
    @Override
    public String order(
            @PathVariable Long clientId,
            @Valid @RequestBody SaleRequest request) {
        return saleService.order(clientId, request.getProductsWithCounts(), request.getTotalCost());
    }
}
