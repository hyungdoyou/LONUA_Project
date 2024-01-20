package com.example.lonua.user.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityDuplicateException;

public class UserDuplicateException extends EntityDuplicateException {

    public UserDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    // 이메일 중복
    public static UserDuplicateException forSignupEmail(String email) {
        return new UserDuplicateException(ErrorCode.DUPLICATE_SIGNUP_EMAIL, String.format("SignUp Email [ %s ] is duplicated.", email));
    }

    // 전화번호 중복
    public static UserDuplicateException forPhoneNumber(String phoneNumber) {
        return new UserDuplicateException(ErrorCode.DUPLICATE_SIGNUP_PHONENUMBER, String.format("SignUp PhoneNumber [ %s ] is duplicated.", phoneNumber));
    }
}
