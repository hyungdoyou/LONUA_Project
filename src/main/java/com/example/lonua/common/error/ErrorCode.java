package com.example.lonua.common.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 공통
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON-001", "유효성 검증에 실패한 경우"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-002", "서버에서 처리할 수 없는 경우"),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "COMMON-003", "테이블 컬럼의 제약조건을 위반 한 경우"),

    // 계정
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ACCOUNT-001", "인증에 실패한 경우"),
    TOKEN_NOT_EXISTS(HttpStatus.UNAUTHORIZED, "ACCOUNT-002", "토큰이 존재하지 않는 경우"),
    INVALID_VERIFICATION_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-003", "토큰이 유효하지 않은 경우"),
    EXPIRED_VERIFICATION_TOKEN(HttpStatus.UNAUTHORIZED, "ACCOUNT-004", "토큰의 유효기간이 만료된 경우"),


    // 회원
    DUPLICATE_SIGNUP_EMAIL(HttpStatus.BAD_REQUEST, "USER-001", "회원 이메일이 중복된 경우"),
    DUPLICATE_SIGNUP_PHONENUMBER(HttpStatus.BAD_REQUEST, "USER-002", "회원 전화번호가 중복된 경우"),
    USER_NOT_EXISTS(HttpStatus.NOT_FOUND, "USER-003", "회원을 찾을 수 없는 경우"),
    DIFFERENT_USER_PASSWORD(HttpStatus.BAD_REQUEST, "USER-004", "회원의 패스워드가 저장된 데이터와 다른 경우"),

    // 판매자
    DUPLICATE_SELLER_SIGNUP_EMAIL(HttpStatus.BAD_REQUEST, "SELLER-001", "판매자 이메일이 중복된 경우"),
    SELLER_NOT_EXISTS(HttpStatus.NOT_FOUND, "SELLER-002", "판매자 계정을 찾을 수 없는 경우"),
    DIFFERENT_SELLER_PASSWORD(HttpStatus.BAD_REQUEST, "SELLER-003", "판매자의 패스워드가 저장된 데이터와 다른 경우"),
    DIFFERENT_BRAND_UNIQUEKEY(HttpStatus.BAD_REQUEST, "SELLER-004", "판매자의 브랜드 고유키가 저장된 데이터와 다른 경우"),

    // 질문
    QUESTION_NOT_EXISTS(HttpStatus.NOT_FOUND, "QUESTION-001", "질문을 찾을 수 없는 경우"),

    // 답변
    ANSWER_NOT_EXISTS(HttpStatus.NOT_FOUND, "ANSWER-001", "답변을 찾을 수 없는 경우"),

    // 브랜드
    BRAND_NOT_EXISTS(HttpStatus.NOT_FOUND, "BRAND-001", "브랜드를 찾을 수 없는 경우"),

    // 리뷰
    REVIEW_NOT_EXISTS(HttpStatus.NOT_FOUND, "REVIEW-001", "리뷰를 찾을 수 없는 경우"),

    // 좋아요
    LIKES_NOT_EXISTS(HttpStatus.NOT_FOUND, "LIKES-001", "좋아요를 찾을 수 없는 경우"),

    // 상품
    PRODUCT_NOT_EXISTS(HttpStatus.NOT_FOUND, "PRDOCUT-001", "상품을 찾을 수 없는 경우"),

    // 주문
    ORDERS_NOT_EXISTS(HttpStatus.NOT_FOUND, "ORDERS-001", "주문번호를 찾을 수 없는 경우"),

    // 스타일
    DUPLICATE_STYLE_TYPE(HttpStatus.BAD_REQUEST, "STYLE-001", "스타일 유형이 중복된 경우"),
    STYLE_NOT_EXISTS(HttpStatus.NOT_FOUND, "STYLE-002", "스타일을 찾을 수 없는 경우"),

    // 카테고리
    DUPLICATE_CATEGORY_NAME(HttpStatus.BAD_REQUEST, "CATEGORY-001", "카테고리 이름이 중복된 경우"),
    CATEGORY_NOT_EXISTS(HttpStatus.NOT_FOUND, "CATEGORY-002", "카테고리를 찾을 수 없는 경우");



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
