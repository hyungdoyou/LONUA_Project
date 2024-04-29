package com.example.lonua.common.error.exception;

import com.example.lonua.common.error.ErrorCode;

public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
