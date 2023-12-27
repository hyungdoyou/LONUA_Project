package com.example.lonua.exception;

import com.example.lonua.exception.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvise extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvise.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity handleUserException(UserException e) {

        log.error("입력한 ID가 중복되었습니다.");
        return makeResponseEntity(e.getErrorCode());
    }

    public ResponseEntity makeResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build());
    }
}
