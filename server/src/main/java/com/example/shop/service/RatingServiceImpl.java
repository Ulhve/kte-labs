package com.example.shop.service;

import com.example.shop.dao.entity.Product;
import com.example.shop.dao.entity.Rating;
import com.example.shop.dao.repository.OrderRepository;
import com.example.shop.dao.repository.ProductRepository;
import com.example.shop.dao.repository.RatingRepository;
import com.example.shop.dto.RatingDTO;
import com.example.shop.dto.RatingWithCount;
import com.example.shop.exception.CustomerNotBoughtProductException;
import com.example.shop.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final ClientService clientService;
    private final ProductRepository productRepository;
    private final OrderRepository checkRepository;
    private final DozerBeanMapper mapper;

    @Override
    public Integer getRatingByClientIdAndProductId(long clientId, long productId) {
        return ratingRepository.findByClientIdAndProductId(clientId, productId)
                .map(Rating::getRating)
                .orElse(null);
    }

    @Override
    public List<RatingDTO> findAllByProductId(long productId) {
        return ratingRepository.findAllByProductId(productId)
                .stream()
                .map(rating -> mapper.map(rating, RatingDTO.class))
                .toList();
    }

    @Override
    public List<RatingWithCount> getRatingsWithCounts(long productId) {
        List<RatingDTO> ratings = findAllByProductId(productId);
        return ratings.stream()
                .collect(Collectors.groupingBy(RatingDTO::getRating, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new RatingWithCount(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public BigDecimal calculateAverageRating(long productId) {
        List<RatingDTO> ratings = findAllByProductId(productId);
        return  BigDecimal.valueOf(
                ratings.stream()
                        .map(RatingDTO::getRating)
                        .flatMapToInt(IntStream::of)
                        .average()
                        .orElse(0)
        ).setScale(1, RoundingMode.HALF_UP);
    }

    @Override
    public RatingDTO rateProduct(RatingDTO rating) {
        Long clientId = rating.getClientId();
        Long productId = rating.getProductId();
        Integer ratingValue = rating.getRating();
        if (!isCustomerBoughtThisProduct(clientId, productId)) {
            throw new CustomerNotBoughtProductException(clientId, productId);
        }

        RatingDTO ratingDTO = null;
        if (rating == null) {
            ratingRepository.deleteByClientIdAndProductId(clientId, productId);
        } else {
            Rating r = ratingRepository.findByClientIdAndProductId(clientId, productId)
                    .map(rElem -> {
                        rElem.setRating(ratingValue);
                        return rElem;
                    })
                    .orElseGet(() -> {
                        Product product = productRepository.findById(productId)
                                .orElseThrow(() -> new ProductNotFoundException(productId));
                        return new Rating(ratingValue, product, clientService.findClientById(clientId));
                    });
            ratingRepository.save(r);
            ratingDTO = mapper.map(r, RatingDTO.class);
        }
        return ratingDTO;
    }

    private boolean isCustomerBoughtThisProduct(long clientId, long productId) {
        return checkRepository.getStatistics(clientId, productId).getCountChecks()>0;
    }
}
