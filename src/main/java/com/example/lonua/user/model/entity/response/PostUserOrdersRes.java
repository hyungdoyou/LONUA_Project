package com.example.lonua.user.model.entity.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserOrdersRes {

    private Integer userIdx;
    private String userEmail;
    private String userName;
    private String userPhoneNumber;
    private String userAddr;
}
