package com.example.lonua.review.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class ReviewNotFoundException extends EntityNotFoundException {
    public ReviewNotFoundException(Integer idx) {
        super(ErrorCode.REVIEW_NOT_EXISTS, String.format("ReviewIdx [ %s ] is not exists.", idx));
    }
}
