package com.example.lonua.likes.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class LikesNotFoundException extends EntityNotFoundException {
    public LikesNotFoundException(Integer idx) {
        super(ErrorCode.LIKES_NOT_EXISTS, String.format("LikesIdx [ %s ] is not exists.", idx));
    }
}
