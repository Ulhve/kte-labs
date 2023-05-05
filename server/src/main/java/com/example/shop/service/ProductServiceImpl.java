package com.example.shop.service;

import com.example.shop.dto.*;
import com.example.shop.dao.entity.Product;
import com.example.shop.dao.repository.ProductRepository;
import com.example.shop.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingService ratingService;
    private final DozerBeanMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .toList();
    }

    @Override
    public Product findProductById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public ProductInfo getProductInfo(long productId, long clientId) {
        Product product = findProductById(productId);

        List<RatingWithCount> ratingsWithCount = ratingService.getRatingsWithCounts(productId);
        BigDecimal averageRating = ratingService.calculateAverageRating(productId);
        Integer currentRating = ratingService.getRatingByClientIdAndProductId(clientId, productId);

        return new ProductInfo(product.getTitle(), averageRating, ratingsWithCount, currentRating);
    }
}
