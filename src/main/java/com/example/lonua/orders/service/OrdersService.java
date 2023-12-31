package com.example.lonua.orders.service;


import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.request.PostRegisterOrdersReq;
import com.example.lonua.orders.model.response.GetListOrdersRes;
import com.example.lonua.orders.model.response.GetReadOrdersRes;
import com.example.lonua.orders.repository.OrdersRepository;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.PostUserLoginRes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public List<GetListOrdersRes> list() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Integer userIdx = user.getUserIdx();

        List<Orders> result = ordersRepository.findAll();

        List<GetListOrdersRes> getListOrdersResList = new ArrayList<>();

        for(Orders orders : result) {
            User loginUser = orders.getUser();

            if(loginUser.getUserIdx() == userIdx) {
                GetReadOrdersProductRes getReadOrdersProductRes = GetReadOrdersProductRes.builder()
                        .productIdx(orders.getProduct().getProductIdx())
                        .productName(orders.getProduct().getProductName())
                        .price(orders.getProduct().getPrice())
                        .build();

                GetListOrdersRes getListOrdersRes = GetListOrdersRes.builder()
                        .ordersIdx(orders.getOrdersIdx())
                        .getReadOrdersProductRes(getReadOrdersProductRes)
                        .build();

                getListOrdersResList.add(getListOrdersRes);
            }
        }
        return getListOrdersResList;
    }

    public GetReadOrdersRes read(Integer ordersIdx) {
        Optional<Orders> result = ordersRepository.findByOrdersIdx(ordersIdx);

        if(result.isPresent()) {
            Orders orders = result.get();

            GetReadOrdersRes response = GetReadOrdersRes.builder()
                    .ordersIdx(orders.getOrdersIdx())
                    .postUserLoginRes(PostUserLoginRes.builder()
                            .userIdx(orders.getUser().getUserIdx())
                            .userId(orders.getUser().getUserId())
                            .userName(orders.getUser().getUsername())
                            .userPhoneNumber(orders.getUser().getUserPhoneNumber())
                            .userAddr(orders.getUser().getUserAddr())
                            .build())
                    .getReadOrdersProductRes(GetReadOrdersProductRes.builder()
                            .productIdx(orders.getProduct().getProductIdx())
                            .brandName(orders.getProduct().getBrand().getBrandName())
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
