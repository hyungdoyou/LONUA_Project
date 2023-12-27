package com.example.lonua.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
}

