package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdersRepositoryCustom  {
    Page<Orders> findList(Pageable pageable, Integer idx);

    Optional<Orders> findOrders(Integer userIdx, Integer ordersIdx, Integer productIdx);
}
