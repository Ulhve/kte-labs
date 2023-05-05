package com.example.shop.configuration;

import com.example.shop.dao.entity.ProductDiscount;
import com.example.shop.dao.entity.Rating;
import com.example.shop.dto.ProductWithDiscount;
import com.example.shop.dto.RatingDTO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(objectMappingBuilder);
        return mapper;
    }

    private BeanMappingBuilder objectMappingBuilder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(Rating.class, RatingDTO.class)
                    .fields("product.id", "productId")
                    .fields("client.id", "clientId");

            mapping(ProductDiscount.class, ProductWithDiscount.class)
                    .fields("product.id", "productId");

        }
    };
}
