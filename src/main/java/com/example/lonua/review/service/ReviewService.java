package com.example.lonua.review.service;


import com.example.lonua.product.model.entity.Product;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.review.model.request.PostReviewReq;
import com.example.lonua.review.repository.ReviewRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review registerReview(PostReviewReq request) {

        return reviewRepository.save(Review.builder()
                .user(User.builder().userIdx(request.getUser().getUserIdx()).build())
                .product(Product.builder().productIdx(request.getProduct().getProductIdx()).build())
                .reviewContent(request.getReviewContent())
                .reviewPhoto(request.getReviewPhoto())
                .evaluation(request.getEvaluation())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build());

    }

    public PostReviewReq readReview(Integer reviewIdx) {
        Optional<Review> result = reviewRepository.findByReviewIdx(reviewIdx);
        if (result.isPresent()) {
            Review review = result.get();

            return PostReviewReq.builder()
                    .reviewIdx(review.getReviewIdx())
                    .reviewContent(review.getReviewContent())
                    .reviewPhoto(review.getReviewPhoto())
                    .evaluation(review.getEvaluation())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .build();
        } else {
            return null;
        }
    }

    public Review updateReview(PostReviewReq request) {
        Optional<Review> result = reviewRepository.findByReviewIdx(request.getReviewIdx());
        if (result.isPresent()) {
            Review review = result.get();
            review.setReviewContent(request.getReviewContent());
            review.setReviewPhoto(request.getReviewPhoto());
            review.setEvaluation(request.getEvaluation());

            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(Integer reviewIdx) {
        reviewRepository.delete(Review.builder().reviewIdx(reviewIdx).build());
    }
}
