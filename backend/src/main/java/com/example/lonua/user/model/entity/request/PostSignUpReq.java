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

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Length(max=45, message = "이메일은 최대 45글자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식( test@example.com )으로 입력해주세요.")
    @ApiModelProperty(value = "이메일", example = "test@example.com", required = true)
    private String userEmail;


    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    @Length(min=8, max=45 , message = "패스워드는 8글자 이상, 45글자 이하여야 합니다.")
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$", message = "패스워드는 대/소문자, 특수문자, 숫자를 반드시 포함한 8글자 이상이어야 합니다.")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String userPassword;


    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Length(max=45, message = "이름은 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String userName;


    @NotNull(message = "생년월일은 필수 입력 항목입니다.")
    @Length(max=10, message = "생년월일은 최대 10글자 이하여야 합니다.")
    @Pattern(regexp = "^(19\\d\\d|20[01]\\d)-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "올바른 날짜 형식을 사용하세요. (yyyy-mm-dd).")
    @ApiModelProperty(value = "생년월일", example = "1999-01-01", required = true)
    private String userBirth;

    @NotNull(message = "성별은 필수 입력 항목입니다.")
    @Length(max=1, message = "성별은 반드시 1글자 여야 합니다. ( M 또는 W )")
    @Pattern(regexp = "^[MW]$", message = "'M' 또는 'W'로 입력하세요.") //TODO 정규표현 식 입력해야함. (M or W)
    @ApiModelProperty(value = "성별", example = "M", required = true)
    private String userGender;

    @NotNull(message = "휴대전화번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식으로 입력하세요.")
    @ApiModelProperty(value = "휴대전화 번호", example = "010-1234-5678", required = true)
    private String userPhoneNumber;

    @NotNull(message = "주소는 필수 입력 항목입니다.")
    @Length(max=45, message = "주소는 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "주소", example = "서울 동작구 보라매로 87 ", required = true)
    private String userAddr;

    @NotNull(message = "선호 스타일은 필수 입력 항목입니다.")
    @Length(max=7, message = "스타일은 최대 7글자 이하여야 합니다.")
    @Pattern(regexp = "^(캐주얼|시크|댄디|스트릿|비지니스캐주얼|힙합|페미닌|오피스|스포츠)$" , message = "올바른 스타일을 입력하세요. (캐주얼 / 시크 / 댄디 / 스트릿 / 비지니스캐주얼 / 힙합 / 페미닌 / 오피스 / 스포츠 )")
    @ApiModelProperty(value = "선호 스타일", example = "캐주얼", required = true)
    private String preferStyle;

    @NotNull(message = "상체 유형은 필수 입력 항목입니다.")
    @Range(min = 1,max = 3, message = "상체 유형은 1 ~ 3까지만 입력 가능합니다.")
    @ApiModelProperty(value = "상체 유형(1 ~ 3)", example = "1", required = true)
    private Integer upperType;

    @NotNull(message = "하체 유형은 필수 입력 항목입니다.")
    @Range(min = 1,max = 3, message = "하체 유형은 1 ~ 3까지만 입력 가능합니다.")
    @ApiModelProperty(value = "하체 유형(1 ~ 3)", example = "1", required = true)
    private Integer lowerType;
}
