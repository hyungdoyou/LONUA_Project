package com.example.lonua.seller.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityDuplicateException;

public class SellerEmailDuplicateException extends EntityDuplicateException {
    public SellerEmailDuplicateException(String email) {
        super(ErrorCode.DUPLICATE_SELLER_SIGNUP_EMAIL, String.format("seller SignUp Email [ %s ] is duplicated.", email));
    }
}
