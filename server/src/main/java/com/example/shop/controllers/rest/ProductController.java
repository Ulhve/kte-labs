package com.example.shop.controllers.rest;

import com.example.shop.controllers.ProductAPI;
import com.example.shop.dto.ProductInfo;
import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.RatingDTO;
import com.example.shop.service.ProductService;
import com.example.shop.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/products")
@RequiredArgsConstructor
public class ProductController implements ProductAPI {

    private final ProductService productService;
    private final RatingService ratingService;

    @GetMapping
    @Override
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    @Override
    public ProductInfo getInfo(
            @PathVariable Long productId,
            @RequestParam(required = true) final Long clientId) {
        return productService.getProductInfo(clientId, productId);
    }

    @PatchMapping("/{productId}")
    @Override
    public RatingDTO rateProduct(
            @PathVariable Long productId,
            @Valid @RequestBody RatingDTO ratingDTO) {
        return ratingService.rateProduct(ratingDTO);
    }

}
