package com.example.shop.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
public class ClientDTO {
    private Long id;
    private String name;
    private Integer personalDiscount1;
    private Integer personalDiscount2;
}
