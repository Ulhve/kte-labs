package com.example.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

    private Integer rating;
    private Long productId;
    private Long clientId;

}
