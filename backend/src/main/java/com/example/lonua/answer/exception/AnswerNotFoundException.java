package com.example.lonua.answer.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class AnswerNotFoundException extends EntityNotFoundException {
    public AnswerNotFoundException(Integer idx) {
        super(ErrorCode.ANSWER_NOT_EXISTS, String.format("AnswerIdx [ %s ] is not exists.", idx));
    }
}
