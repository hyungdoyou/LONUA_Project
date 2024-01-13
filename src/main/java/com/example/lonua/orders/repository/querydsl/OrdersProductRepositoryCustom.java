package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.OrdersProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersProductRepositoryCustom {
    Page<OrdersProduct> findList(Pageable pageable);

    Optional<OrdersProduct> findOrdersProduct(Integer idx);
}
