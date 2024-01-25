package com.example.lonua.seller.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class SellerNotFoundException extends EntityNotFoundException {
    public SellerNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static SellerNotFoundException forEmail(String email) {
        return new SellerNotFoundException(ErrorCode.SELLER_NOT_EXISTS, String.format("seller email [ %s ] is not exists.", email));
    }

    public static SellerNotFoundException forIdx(Integer idx) {
        return new SellerNotFoundException(ErrorCode.SELLER_NOT_EXISTS, String.format("SellerIdx [ %s ] is not exists.", idx));
    }
}
