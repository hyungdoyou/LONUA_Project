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

    private Integer preferStyle;

    private Integer upperType;

    private Integer lowerType;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;

    private String status;
}
