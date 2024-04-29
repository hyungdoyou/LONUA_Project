package com.example.lonua.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmailVerifyRes {

    private String email;
    private Boolean status;
}
