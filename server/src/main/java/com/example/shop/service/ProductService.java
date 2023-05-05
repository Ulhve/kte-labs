package com.example.shop.service;

import com.example.shop.dto.ProductInfo;
import com.example.shop.dao.entity.Product;
import com.example.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    Product findProductById(long productId);
    ProductInfo getProductInfo(long productId, long clientId);
}
