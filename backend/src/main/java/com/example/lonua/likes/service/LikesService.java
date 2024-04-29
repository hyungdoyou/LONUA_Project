package com.example.lonua.likes.service;


import com.example.lonua.common.BaseRes;
import com.example.lonua.likes.exception.LikesNotFoundException;
import com.example.lonua.likes.model.entity.Likes.Likes;
import com.example.lonua.likes.model.request.PostCancelLikesReq;
import com.example.lonua.likes.model.response.GetListLikesRes;
import com.example.lonua.likes.repository.LikesRepository;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.ProductCount;
import com.example.lonua.product.repository.ProductCountRepository;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final ProductRepository productRepository;
    private final ProductCountRepository productCountRepository;
    private final LikesRepository likesRepository;

    @Transactional(readOnly = false)
    public BaseRes createLikes(User user, Integer productIdx) {
        Optional<Product> result = productRepository.findByProductIdx(productIdx);
        if (result.isPresent()) {
            Product product = result.get();

            Optional<Likes> byUserAndProduct = likesRepository.findByUserAndProduct(user, product);
            if (byUserAndProduct.isPresent()) {
                return BaseRes.builder()
                        .code(400)
                        .isSuccess(false)
                        .message("요청 실패")
                        .result("이미 좋아요를 누른 상품입니다.")
                        .build();
            } else {
                ProductCount productCount = product.getProductCount();
                productCount.increaseLikeCount();
                productCountRepository.save(productCount);

                likesRepository.save(Likes.builder()
                        .product(product)
                        .user(user)
                        .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                        .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                        .status(true)
                        .build());
                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("요청 성공")
                        .result("상품에 좋아요를 추가하였습니다.")
                        .build();
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public BaseRes list(User user) {
        List<Likes> likesList = likesRepository.findAll();

        List<GetListLikesRes> getListLikesResList = new ArrayList<>();
        if(likesList != null) {
            for (Likes likes : likesList) {
                if (likes.getUser().getUserIdx().equals(user.getUserIdx())) {
                    GetListLikesRes getListLikesRes = GetListLikesRes.builder()
                            .likesIdx(likes.getLikesIdx())
                            .brandName(likes.getProduct().getBrand().getBrandName())
                            .productIdx(likes.getProduct().getProductIdx())
                            .productName(likes.getProduct().getProductName())
                            .productImage(likes.getProduct().getProductImageList().get(0).getProductImage())
                            .price(likes.getProduct().getPrice())
                            .salePrice(likes.getProduct().getSalePrice())
                            .build();
                    getListLikesResList.add(getListLikesRes);
                }
            }
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListLikesResList)
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("좋아요 목록을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Transactional(readOnly = false)
    public BaseRes cancle(Integer productIdx, User user) {

        Integer result = likesRepository.deleteByProduct_productIdxAndUser(productIdx, user);

        if (!result.equals(0)) {
            Optional<Product> product = productRepository.findByProductIdx(productIdx);

            Product cancelProduct = product.get();
            ProductCount productCount = cancelProduct.getProductCount();
            productCount.decreaseLikeCount();
            productCountRepository.save(productCount);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("좋아요를 취소하였습니다.")
                    .build();
        } else {
            throw new LikesNotFoundException(productIdx);
        }
    }
}
