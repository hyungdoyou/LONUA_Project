package com.example.lonua.common.error.exception;

import com.example.lonua.common.error.ErrorCode;

public class EntityDuplicateException extends BusinessException{

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
