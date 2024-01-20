package com.example.lonua.common.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 공통
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON-001", "유효성 검증에 실패한 경우"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-002", "서버에서 처리할 수 없는 경우"),

    // 계정
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ACCOUNT-001", "인증에 실패한 경우"),
    ROLE_NOT_EXISTS(HttpStatus.FORBIDDEN, "ACCOUNT-002", "권한이 없는 경우"),
    TOKEN_NOT_EXISTS(HttpStatus.UNAUTHORIZED, "ACCOUNT-003", "토큰이 존재하지 않는 경우"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-004", "토큰이 유효하지 않은 경우"),

    // 회원
    DUPLICATE_SIGNUP_EMAIL(HttpStatus.BAD_REQUEST, "USER-001", "회원 이메일이 중복된 경우"),
    DUPLICATE_SIGNUP_PHONENUMBER(HttpStatus.BAD_REQUEST, "USER-002", "회원 전화번호가 중복된 경우"),

    // 스타일
    DUPLICATE_STYLE_TYPE(HttpStatus.BAD_REQUEST, "STYLE-001", "스타일 유형이 중복된 경우"),
    STYLE_IDX_NOT_EXISTS(HttpStatus.BAD_REQUEST, "STYLE-002", "스타일이 존재하지 않는 경우"),

    // 카테고리
    DUPLICATE_CATEGORY_NAME(HttpStatus.BAD_REQUEST, "CATEGORY-001", "카테고리 이름이 중복된 경우"),
    CATEGORY_IDX_NOT_EXISTS(HttpStatus.BAD_REQUEST, "CATEGORY-002", "카테고리가 존재하지 않는 경우");
    private final HttpStatus status;  // 헤더로 반환할 Http 상태 코드
    private final String code;    // 페이로드로 반환할 에러 코드
    private final String description;  // 에러 코드 문서화를 위한 설명

    ErrorCode(HttpStatus status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public HttpStatus getStatus() { return status; }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
