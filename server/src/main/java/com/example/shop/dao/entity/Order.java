package com.example.shop.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NonNull
    private Client client;

    @Column
    @NonNull
    private LocalDateTime date;

    @Column
    @NonNull
    private String checkNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_positions_id")
    private List<OrderPosition> positions;
}
