package com.example.lonua.style.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityDuplicateException;

public class StyleDuplicateException extends EntityDuplicateException {
    public StyleDuplicateException(String styleType) {
        super(ErrorCode.DUPLICATE_STYLE_TYPE, String.format("StyleType [ %s ] is duplicated.", styleType));
    }
}
