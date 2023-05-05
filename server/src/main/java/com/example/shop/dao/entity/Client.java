package com.example.shop.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Min(1)
    @Max(100)
    @Column(name = "discount_1")
    private Integer discount1;

    @Min(1)
    @Max(100)
    @Column(name = "discount_2")
    private Integer discount2;
}
