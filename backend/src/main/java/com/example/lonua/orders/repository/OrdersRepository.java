package com.example.lonua.orders.repository;

import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.repository.querydsl.OrdersProductRepositoryCustom;
import com.example.lonua.orders.repository.querydsl.OrdersRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>, OrdersRepositoryCustom {
    public Integer deleteByOrdersIdx(Integer idx);

    public Optional<Orders> findByOrdersIdx(Integer idx);
}
