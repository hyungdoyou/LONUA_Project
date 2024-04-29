package com.example.lonua.category.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityDuplicateException;

public class CategoryDuplicateException extends EntityDuplicateException {
    public CategoryDuplicateException(String categoryName) {
        super(ErrorCode.DUPLICATE_CATEGORY_NAME, String.format("Category name [ %s ] is duplicated.", categoryName));
    }
}
