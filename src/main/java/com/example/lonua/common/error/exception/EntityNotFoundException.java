package com.example.lonua.common.error.exception;

import com.example.lonua.common.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
