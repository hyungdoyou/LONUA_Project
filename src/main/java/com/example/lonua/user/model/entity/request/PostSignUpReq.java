package com.example.lonua.user.model.entity.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignUpReq {


    private String userEmail;

    private String userPassword;

    private String userName;

    private String userBirth;

    private String userGender;

    private String userPhoneNumber;

    private String userAddr;

    private String preferStyle;

    private Integer upperType;

    private Integer lowerType;
}
