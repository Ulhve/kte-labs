package com.example.shop.dao.repository;

import com.example.shop.dao.entity.Order;
import com.example.shop.dto.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.date > :saleDate ORDER BY o.date DESC LIMIT 1")
    Optional<String> findLastCheckToday(@Param("saleDate") LocalDateTime saleDate);

    @Query("SELECT new com.example.shop.dto.Statistics(" +
            "count(o.checkNumber), " +
            "sum(p.finalPrice * p.count), " +
            "sum(p.finalDiscountPercent)" +
            ") " +
            "FROM Order o " +
            "INNER JOIN o.positions p " +
            "WHERE o.client.id = ?1 " +
            "AND p.product.id = ?2 ")
    Statistics getStatistics(Long client_id, Long product_id);

    @Query("SELECT new com.example.shop.dto.Statistics(" +
            "count(o.checkNumber), " +
            "sum(p.finalPrice * p.count), " +
            "sum(p.finalDiscountPercent)" +
            ") " +
            "FROM Order o " +
            "INNER JOIN o.positions p " +
            "WHERE p.product.id = :product_id ")
    Statistics getProductStatistics(@Param("product_id") Long productId);

    @Query("SELECT new com.example.shop.dto.Statistics(" +
            "count(o.checkNumber), " +
            "sum(p.finalPrice * p.count), " +
            "sum(p.finalDiscountPercent)" +
            ") " +
            "FROM Order o " +
            "INNER JOIN o.positions p " +
            "WHERE o.client.id = :client_id ")
    Statistics getClientStatistics(@Param("client_id") Long clientId);
}
