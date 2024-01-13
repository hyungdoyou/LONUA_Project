package com.example.lonua.review.service;


import com.example.lonua.config.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.review.model.request.PostReviewReq;
import com.example.lonua.review.model.response.PostReviewRes;
import com.example.lonua.review.repository.ReviewRepository;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.PostSignupRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public BaseRes registerReview(PostReviewReq request) {

         reviewRepository.save(Review.builder()
                .user(User.builder().userIdx(request.getUser().getUserIdx()).build())
                .product(Product.builder().productIdx(request.getProduct().getProductIdx()).build())
                .reviewContent(request.getReviewContent())
                .reviewPhoto(request.getReviewPhoto())
                .evaluation(request.getEvaluation())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build());

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("리뷰를 등록했습니다.")
                .result(request)
                .build();

        return baseRes;

    }

    public BaseRes readReview(Integer reviewIdx) {
        Optional<Review> result = reviewRepository.findByReviewIdx(reviewIdx);
        if (result.isPresent()) {
            Review review = result.get();

             PostReviewReq.builder()
                .reviewIdx(review.getReviewIdx())
                .reviewContent(review.getReviewContent())
                .reviewPhoto(review.getReviewPhoto())
                .evaluation(review.getEvaluation())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("리뷰를 가져왔습니다.")
                    .result(reviewIdx)
                    .build();

            return baseRes;

        } else {
            return null;
        }

    }

    public BaseRes updateReview(PostReviewReq request) {
        Optional<Review> result = reviewRepository.findByReviewIdx(request.getReviewIdx());
        if (result.isPresent()) {
            Review review = result.get();
            review.setReviewContent(request.getReviewContent());
            review.setReviewPhoto(request.getReviewPhoto());
            review.setEvaluation(request.getEvaluation());

            reviewRepository.save(review);

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("리뷰를 가져왔습니다.")
                    .result(request)
                    .build();

            return baseRes;

        }
        return null;
    }

    public BaseRes deleteReview(Integer reviewIdx) {
        reviewRepository.delete(Review.builder().reviewIdx(reviewIdx).build());

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("리뷰를 삭제했습니다.")
                .result(reviewIdx)
                .build();

        return baseRes;
    }
}
