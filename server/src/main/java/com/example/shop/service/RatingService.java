package com.example.shop.service;

import com.example.shop.dao.entity.Rating;
import com.example.shop.dto.RatingDTO;
import com.example.shop.dto.RatingWithCount;

import java.math.BigDecimal;
import java.util.List;

public interface RatingService {
    Integer getRatingByClientIdAndProductId(long clientId, long productId);
    List<RatingDTO> findAllByProductId(long productId);
    List<RatingWithCount> getRatingsWithCounts (long productId);
    BigDecimal calculateAverageRating(long productId);
    RatingDTO rateProduct(RatingDTO rating);
}
