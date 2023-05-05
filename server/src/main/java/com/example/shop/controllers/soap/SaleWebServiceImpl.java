package com.example.shop.controllers.soap;

import com.example.shop.controllers.SaleAPI;
import com.example.shop.controllers.request.SaleRequest;
import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.Statistics;
import com.example.shop.service.SaleService;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", serviceName = "SoapService", portName = "soapPort",
        endpointInterface = "com.example.shop.controllers.soap.SaleWebService")
@RequiredArgsConstructor
@Service
public class SaleWebServiceImpl implements SaleWebService, SaleAPI {

    private final SaleService saleService;

    @Override
    public Long calculate(Long clientId, List<ProductWithCount> products){
        return saleService.calculateFinalPrice(clientId, products);
    }

    @Override
    public Statistics statistics(ClientWithProduct request) {
        return saleService.getStatistics(request);
    }

    @Override
    public String order(Long clientId, SaleRequest request) {
        return saleService.order(clientId, request.getProductsWithCounts(), request.getTotalCost());
    }

}
