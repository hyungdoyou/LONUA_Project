package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.orders.model.entity.OrdersProduct;

import java.util.Optional;

public interface OrdersProductRepositoryCustom {
    Optional<OrdersProduct> findOrdersProduct(Integer userIdx, Integer ordersIdx, Integer productIdx);
}
