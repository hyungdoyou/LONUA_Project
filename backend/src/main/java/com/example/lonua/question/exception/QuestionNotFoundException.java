package com.example.lonua.question.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class QuestionNotFoundException extends EntityNotFoundException {
    public QuestionNotFoundException(Integer idx) {
        super(ErrorCode.QUESTION_NOT_EXISTS, String.format("QuestionIdx [ %s ] is not exists.", idx));
    }
}
