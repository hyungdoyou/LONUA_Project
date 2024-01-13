package com.example.lonua.review.controller;


import com.example.lonua.review.model.request.PostReviewReq;
import com.example.lonua.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity readReview(PostReviewReq request) {

        return ResponseEntity.ok().body(reviewService.registerReview(request));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity readReview(Integer reviewIdx) {
        return ResponseEntity.ok().body(reviewService.readReview(reviewIdx));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity updateReview(PostReviewReq request) {
        return ResponseEntity.ok().body(reviewService.updateReview(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity deleteReview(Integer reviewIdx) {
        reviewService.deleteReview(reviewIdx);
        return ResponseEntity.ok().body("리뷰가 삭제 되었습니다.");
    }
}
