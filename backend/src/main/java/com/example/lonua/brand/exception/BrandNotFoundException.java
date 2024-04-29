package com.example.lonua.brand.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class BrandNotFoundException extends EntityNotFoundException {
    public BrandNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static BrandNotFoundException forIdx(Integer idx) {
        return new BrandNotFoundException(ErrorCode.BRAND_NOT_EXISTS, String.format("BrandIdx [ %s ] is not exists.", idx));
    }
}
