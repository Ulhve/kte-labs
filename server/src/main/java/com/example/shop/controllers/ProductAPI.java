package com.example.shop.controllers;

import com.example.shop.dto.ProductInfo;
import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.RatingDTO;

import java.util.List;

public interface ProductAPI {
    List<ProductDTO> getAll();
    ProductInfo getInfo(Long productId, Long clientId);
    RatingDTO rateProduct(Long productId, RatingDTO ratingDTO);
}
