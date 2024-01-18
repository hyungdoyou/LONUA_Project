package com.example.lonua.likes.service;


import com.example.lonua.common.BaseRes;
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
    public BaseRes createLikes(User user, Integer idx) {
        Optional<Product> result = productRepository.findByProductIdx(idx);
        // Todo 이 제품에 좋아요를 눌렀던적이 있는지 확인하는 로직 추가
        if (result.isPresent()) {
            Product product = result.get();

            Optional<Likes> byUserAndProduct = likesRepository.findByUserAndProduct(user, product);
            if (byUserAndProduct.isPresent()) {
                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("요청 성공")
                        .result("좋아요를 누른적이 있습니다.")
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
                        .result("좋아요를 추가하였습니다.")
                        .build();
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public BaseRes list(User user) {
        List<Likes> likesList = likesRepository.findAll();

        List<GetListLikesRes> getListLikesResList = new ArrayList<>();
        for (Likes likes : likesList) {
            if (likes.getUser().getUserIdx().equals(user.getUserIdx())) {
                GetListLikesRes getListLikesRes = GetListLikesRes.builder()
                        .brandName(likes.getProduct().getBrand().getBrandName())
                        .productName(likes.getProduct().getProductName())
                        .price(likes.getProduct().getPrice())
                        .likeCount(likes.getProduct().getProductCount().getLikeCount())
                        .build();
                getListLikesResList.add(getListLikesRes);
            }
        }
        BaseRes response = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공.")
                .result(getListLikesResList)
                .build();

        return response;
    }

    @Transactional(readOnly = false)
    public BaseRes cancle(User user, PostCancelLikesReq postCancelLikesReq) {

        Integer result = likesRepository.deleteByLikesIdxAndUser(postCancelLikesReq.getLikesIdx(), user);

        if (!result.equals(0)) {
            Optional<Product> product = productRepository.findByProductIdx(postCancelLikesReq.getProductIdx());

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
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("좋아요 취소를 실패 하였습니다.")
                    .build();
        }
    }
}
