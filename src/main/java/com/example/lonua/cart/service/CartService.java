package com.example.lonua.cart.service;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.model.request.DeleteCartRemoveReq;
import com.example.lonua.cart.model.request.PostCartRegisterReq;
import com.example.lonua.cart.model.response.GetCartListRes;
import com.example.lonua.cart.model.response.PostCartRegisterRes;
import com.example.lonua.cart.repository.CartRepository;
import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.user.model.entity.User;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;


    @Transactional(readOnly = false)
    public BaseRes create(User user, PostCartRegisterReq request) {
        Cart cart = cartRepository.save(Cart.builder()
                .user(User.builder()
                        .userIdx(user.getUserIdx())
                        .build())
                .product(Product.builder()
                        .productIdx(request.getProductIdx())
                        .build())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(true)
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(PostCartRegisterRes.builder()
                        .cartIdx(cart.getCartIdx())
                        .createdAt(cart.getCreatedAt())
                        .updatedAt(cart.getUpdatedAt())
                        .userIdx(cart.getUser().getUserIdx())
                        .productIdx(cart.getProduct().getProductIdx())
                        .status(cart.getStatus())
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes list(User user, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);

        Page<Cart> cartList = cartRepository.findList(pageable, user.getUserIdx());
//        Page<Cart> cartList = cartRepository.findAll(pageable);
        List<GetCartListRes> getListResCartList = new ArrayList<>();

        for (Cart cart : cartList) {
            GetCartListRes build = GetCartListRes.builder()
                    .cartIdx(cart.getCartIdx())
                    .brandName(cart.getProduct().getBrand().getBrandName())
                    .productIdx(cart.getProduct().getProductIdx())
                    .productName(cart.getProduct().getProductName())
                    .productImage(cart.getProduct().getProductImageList().get(0).getProductImage())
                    .price(cart.getProduct().getPrice())
                    .salePrice(cart.getProduct().getSalePrice())
                    .build();
            getListResCartList.add(build);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListResCartList)
                .build();
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer cartIdx) {

        Integer result = cartRepository.deleteByCartIdx(cartIdx);

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("장바구니 1개 상품 삭제 성공")
                    .result("요청 성공")
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("장바구니 상품 1개 삭제 실패")
                    .result("요청 실패")
                    .build();
        }
    }


    @Transactional(readOnly = false)
    public BaseRes deleteAll(User user) {

        cartRepository.deleteAllByUserIdx(user.getUserIdx());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("장바구니 전체 상품 삭제 성공")
                .result("요청 성공")
                .build();
    }
}
