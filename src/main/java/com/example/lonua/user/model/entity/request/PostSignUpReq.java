package com.example.lonua.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignUpReq {

    @NotNull
    @Length(max=45)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$") //TODO 정규표현 식 입력해야함.
    @ApiModelProperty(value = "이메일", example = "test@example.com", required = true)
    private String userEmail;


    @NotNull
    @Length(min=8,max=200)
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String userPassword;


    @NotNull
    @Length(max=45)
    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String userName;


    @NotNull
    @Length(max=10)
    @Pattern(regexp = "^(19\\d\\d|20[01]\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "올바른 날짜 형식을 사용하세요. (yyyy-mm-dd).")
    @ApiModelProperty(value = "생년월일", example = "1999-01-01", required = true)
    private String userBirth;

    @NotNull
    @Length(max=1)
    @Pattern(regexp = "^[MW]$", message = "'M' 또는 'W'로 입력하세요.") //TODO 정규표현 식 입력해야함. (M or W)
    @ApiModelProperty(value = "성별", example = "M", required = true)
    private String userGender;

    @NotNull
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    @ApiModelProperty(value = "휴대전화 번호", example = "010-1234-5678", required = true)
    private String userPhoneNumber;

    @NotNull
    @Length(max=45)
    @ApiModelProperty(value = "주소", example = "서울 동작구 보라매로 87 ", required = true)
    private String userAddr;

    @NotNull
    @Length(max=7)
    @Pattern(regexp = "^(캐주얼|시크|댄디|스트릿|비지니스캐주얼|힙합|페미닌|오피스|스포츠)$")
    @ApiModelProperty(value = "선호 스타일", example = "캐주얼", required = true)
    private String preferStyle;

    @NotNull
    @Range(min = 1,max = 3)
    @ApiModelProperty(value = "상체 유형(1 ~ 3)", example = "1", required = true)
    private Integer upperType;

    @NotNull
    @Range(min = 1,max = 3)
    @ApiModelProperty(value = "하체 유형(1 ~ 3)", example = "1", required = true)
    private Integer lowerType;
}
