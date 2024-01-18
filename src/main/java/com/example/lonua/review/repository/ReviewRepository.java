package com.example.lonua.review.repository;

import com.example.lonua.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByReviewIdxAndUser_userIdx(Integer reviewIdx, Integer userIdx);

    Optional<Review> findByReviewIdx(Integer reviewIdx);
    List<Review> findByProduct_ProductIdx(Integer productIdx);

    Integer deleteByReviewIdxAndUser_userIdx(Integer reviewIdx, Integer userIdx);
}
