package com.example.shop.controllers.soap;

import com.example.shop.controllers.ProductAPI;
import com.example.shop.dto.ProductInfo;
import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.RatingDTO;
import com.example.shop.service.ProductService;
import com.example.shop.service.RatingService;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", serviceName = "SoapService", portName = "soapPort",
        endpointInterface = "com.example.shop.controllers.soap.ProductWebService")
@RequiredArgsConstructor
@Service
public class ProductWebServiceImpl implements ProductWebService, ProductAPI {

    private final ProductService productService;
    private final RatingService ratingService;

    @Override
    public List<ProductDTO> getAll(){
        return productService.getAllProducts();
    }

    @Override
    public ProductInfo getInfo(Long productId, Long clientId) {
        return productService.getProductInfo(clientId, productId);
    }

    @Override
    public RatingDTO rateProduct(Long productId, RatingDTO ratingDTO) {
        return ratingService.rateProduct(ratingDTO);
    }

}
