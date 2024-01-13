package com.example.lonua.cart.service;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.model.request.PostRegisterReq;
import com.example.lonua.cart.model.response.PostRegisterRes;
import com.example.lonua.cart.repository.CartRepository;
import com.example.lonua.config.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public BaseRes create(PostRegisterReq request) {
        Cart cart = cartRepository.save(Cart.builder()
                .user(User.builder()
                        .userIdx(request.getUserIdx())
                        .build())
                .product(Product.builder()
                        .productIdx(request.getProductIdx())
                        .build())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build());


        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostRegisterRes.builder()
                        .cartIdx(cart.getCartIdx())
                        .createdAt(cart.getCreatedAt())
                        .updatedAt(cart.getUpdatedAt())
                        .userIdx(cart.getUser().getUserIdx())
                        .productIdx(cart.getProduct().getProductIdx())
                        .status(cart.getStatus())
                        .build())
                .build();
    }
}
