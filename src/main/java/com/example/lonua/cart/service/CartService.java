package com.example.lonua.cart.service;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.model.request.DeleteRemoveReq;
import com.example.lonua.cart.model.request.PostRegisterReq;
import com.example.lonua.cart.model.response.GetListRes;
import com.example.lonua.cart.model.response.PostRegisterRes;
import com.example.lonua.cart.repository.CartRepository;
import com.example.lonua.config.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public BaseRes list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Cart> all = cartRepository.findList(pageable);
        List<GetListRes> getListResList = new ArrayList<>();

        for (Cart cart : all) {
            GetListRes build = GetListRes.builder()
                    .cartIdx(cart.getCartIdx())
                    .productName(cart.getProduct().getProductName())
                    .price(cart.getProduct().getPrice())
                    .build();
            getListResList.add(build);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResList)
                .build();
    }

    public BaseRes delete(DeleteRemoveReq request) {

        Cart cart = Cart.builder()
                .cartIdx(request.getCartIdx())
                .build();

        cartRepository.delete(cart);

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .build();
    }

    public BaseRes deleteAll() {
        cartRepository.deleteAll();

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .build();
    }
}
