package com.example.shop.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Entity
@Table(name = "product_discounts")
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NonNull
    private Product product;

    @Column(nullable = false)
    @Min(5)
    @Max(10)
    @NonNull
    private Integer percentDiscount;
}
