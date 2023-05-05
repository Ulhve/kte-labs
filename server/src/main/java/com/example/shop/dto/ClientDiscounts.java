package com.example.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDiscounts {

    @Min(1)
    @Max(100)
    private Integer discount1;

    @Min(1)
    @Max(100)
    private Integer discount2;

}
