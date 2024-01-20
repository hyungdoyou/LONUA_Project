package com.example.lonua.user.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static UserNotFoundException forEmail(String email) {
        return new UserNotFoundException(ErrorCode.USER_NOT_EXISTS, String.format("User email [ %s ] is not exists.", email));
    }

    public static UserNotFoundException forIdx(Integer idx) {
        return new UserNotFoundException(ErrorCode.USER_NOT_EXISTS, String.format("UserIdx [ %s ] is not exists.", idx));
    }
}
