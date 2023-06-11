package com.example.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;
    private Integer discount1;
    private Integer discount2;

}
