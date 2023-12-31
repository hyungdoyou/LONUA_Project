package com.example.lonua.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 공통
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON-001", "잘못 입력하셨습니다.(유효성 검증 실패"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-002", "서버에서 처리할 수 없습니다."),

    // User
    DUPLICATED_USER(HttpStatus.CONFLICT, "USER-001", "이미 존재하는 사용자 ID입니다."),
    INVALID_PERMISIION(HttpStatus.UNAUTHORIZED, "USER-002", "접근 권한이 없습니다."),

    // Product
    DUPLICATED_PRODUCT(HttpStatus.CONFLICT, "PRODUCT-001", "이미 존재하는 상품명 입니다."),
    ;




    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
