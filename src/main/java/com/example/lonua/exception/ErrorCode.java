package com.example.lonua.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 존재하는 정보입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    INVALID_PERMISIION(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    WRONG_ADDR(HttpStatus.NOT_FOUND,"주소를 잘못입력했습니다.")
    ;


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
