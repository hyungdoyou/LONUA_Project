package com.example.lonua.exception.exception;

import com.example.lonua.exception.ErrorCode;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;


    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }

    }
}
