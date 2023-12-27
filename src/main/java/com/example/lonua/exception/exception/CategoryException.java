package com.example.lonua.exception.exception;

import com.example.lonua.category.model.entity.Category;
import com.example.lonua.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;


    public CategoryException(ErrorCode errorCode) {
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
