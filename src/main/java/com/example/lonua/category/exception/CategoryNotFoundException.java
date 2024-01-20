package com.example.lonua.category.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(Integer idx) {
        super(ErrorCode.CATEGORY_NOT_EXISTS, String.format("CategoryIdx [ %s ] is not exists.", idx));
    }
}
