package com.example.lonua.review.controller;


import com.example.lonua.config.BaseRes;
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
        BaseRes baseRes = reviewService.registerReview(request);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity readReview(Integer reviewIdx) {
        BaseRes baseRes = reviewService.readReview(reviewIdx);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity updateReview(PostReviewReq request) {
        BaseRes baseRes = reviewService.updateReview(request);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity deleteReview(Integer reviewIdx) {
        BaseRes baseRes =  reviewService.deleteReview(reviewIdx);
        return ResponseEntity.ok().body(baseRes);
    }
}
