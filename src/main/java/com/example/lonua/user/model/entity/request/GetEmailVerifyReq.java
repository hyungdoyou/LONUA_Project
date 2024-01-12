package com.example.lonua.user.model.entity.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmailVerifyReq {
    private String email;
    private String uuid;
}
