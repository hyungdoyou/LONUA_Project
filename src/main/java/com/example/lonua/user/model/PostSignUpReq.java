package com.example.lonua.user.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignUpReq {

    private String userId;

    private String userPw;

    private String userName;

    private String userBirth;

    private String userGender;

    private String userPhoneNumber;

    private String userEmail;

    private String userAddr;

    private String preferStyle;

    private Integer upperType;

    private Integer lowerType;
}
