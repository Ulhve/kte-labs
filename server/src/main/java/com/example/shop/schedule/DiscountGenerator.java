package com.example.shop.schedule;

import com.example.shop.dao.entity.Product;
import com.example.shop.dao.entity.ProductDiscount;
import com.example.shop.dao.repository.ProductDiscountRepository;
import com.example.shop.dao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class DiscountGenerator {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductRepository productRepository;
    private final ProductDiscountRepository productDiscountRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void nextProduct() {
        int count = (int) productRepository.count();
        if (count == 0) {
            return;
        }

        Random random = new Random();
        int numberRow = random.nextInt(count);
        Page<Product> pageProduct = productRepository.findAll(PageRequest.of(numberRow, 1));
        if (!pageProduct.hasContent()) {
            return;
        }
        Product product = pageProduct.get().toList().get(0);
        int discount = 4 + random.nextInt(6);

        updateDiscountingProduct(product, discount);
    }

    private void updateDiscountingProduct(Product product, int discount) {
        ProductDiscount productDiscount = new ProductDiscount(product, discount);
        productDiscountRepository.save(productDiscount);
        applicationEventPublisher.publishEvent(new DiscountingProductEvent(this, product.getId(), discount));
    }
}
