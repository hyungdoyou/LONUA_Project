package com.example.lonua.product.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;
import com.example.lonua.user.exception.UserNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static ProductNotFoundException forName(String name) {
        return new ProductNotFoundException(ErrorCode.PRODUCT_NOT_EXISTS, String.format("Product name [ %s ] is not exists.", name));
    }

    public static ProductNotFoundException forIdx(Integer idx) {
        return new ProductNotFoundException(ErrorCode.PRODUCT_NOT_EXISTS, String.format("ProductIdx [ %s ] is not exists.", idx));
    }
}
