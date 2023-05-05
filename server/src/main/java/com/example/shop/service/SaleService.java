package com.example.shop.service;

import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.Statistics;

import java.util.List;

public interface SaleService {

    Long calculateFinalPrice(long clientId, List<ProductWithCount> products);
    String order(long clientId, List<ProductWithCount> products, long finalPriceKopecks);
    Statistics getStatistics(ClientWithProduct clientAndProduct);

}
