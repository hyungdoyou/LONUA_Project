package com.example.lonua.orders.service;


import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.request.PostRegisterOrdersReq;
import com.example.lonua.orders.model.response.PostRegisterOrdersRes;
import com.example.lonua.orders.repository.OrdersRepository;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.response.PostReadProductRes;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.PostUserLoginRes;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void register(Integer userIdx, PostRegisterOrdersReq postRegisterOrdersReq) {

        Orders orders = Orders.builder()
                .user(User.builder().userIdx(userIdx).build())
                .product(Product.builder().productIdx(postRegisterOrdersReq.getProduct_idx()).build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(1)
                .build();
    }

    public PostRegisterOrdersRes read(Integer ordersIdx) {
        Optional<Orders> result = ordersRepository.findByOrdersIdx(ordersIdx);

        if(result.isPresent()) {
            Orders orders = result.get();

            PostRegisterOrdersRes response = PostRegisterOrdersRes.builder()
                    .ordersIdx(orders.getOrdersIdx())
                    .postUserLoginRes(PostUserLoginRes.builder()
                            .userIdx(orders.getUser().getUserIdx())
                            .userId(orders.getUser().getUserId())
                            .userName(orders.getUser().getUsername())
                            .userPhoneNumber(orders.getUser().getUserPhoneNumber())
                            .userAddr(orders.getUser().getUserAddr())
                            .build())
                    .postReadProductRes(PostReadProductRes.builder()
                            .productIdx(orders.getProduct().getProductIdx())
                            .productName(orders.getProduct().getProductName())
                            .price(orders.getProduct().getPrice())
                            .build())
                    .build();

            return response;
        } else {
            return null;
        }
    }
}
