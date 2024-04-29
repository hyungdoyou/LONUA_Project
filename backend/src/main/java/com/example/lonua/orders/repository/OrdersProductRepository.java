package com.example.lonua.orders.repository;

import com.example.lonua.orders.model.entity.OrdersProduct;
import com.example.lonua.orders.repository.querydsl.OrdersProductRepositoryCustom;
import com.example.lonua.orders.repository.querydsl.OrdersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Integer>, OrdersProductRepositoryCustom {
    public Integer deleteByOrders_OrdersIdx(Integer idx);
}
