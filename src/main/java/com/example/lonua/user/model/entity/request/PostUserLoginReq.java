package com.example.lonua.user.model.entity.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserLoginReq {
    private String userId;
    private String userPw;
}