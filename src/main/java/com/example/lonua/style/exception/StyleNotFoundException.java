package com.example.lonua.style.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class StyleNotFoundException extends EntityNotFoundException {
    public StyleNotFoundException(Integer idx) {
        super(ErrorCode.STYLE_NOT_EXISTS, String.format("StyleIdx [ %s ] is not exists.", idx));
    }
}
