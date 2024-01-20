package com.example.lonua.Seller.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.BusinessException;
import com.example.lonua.user.exception.UserAccountException;

public class SellerAccountException extends BusinessException {
    public SellerAccountException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static SellerAccountException forInvalidUniqueKey(String uniqueKey) {
        return new SellerAccountException(ErrorCode.DIFFERENT_BRAND_UNIQUEKEY, String.format("Brand UniqueKey [ %s ] is different.", uniqueKey));
    }

    public static SellerAccountException forInvalidPassword(String password) {
        return new SellerAccountException(ErrorCode.DIFFERENT_SELLER_PASSWORD, String.format("Seller Password [ %s ] is different.", password));
    }
}
