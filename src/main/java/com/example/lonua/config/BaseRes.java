package com.example.lonua.config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseRes {
    private Integer code;
    private Boolean isSuccess;
    private String message;
    private Object result;
}
