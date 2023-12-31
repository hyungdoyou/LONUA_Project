package com.example.lonua.exception.exception;

import com.example.lonua.exception.ErrorCode;

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
