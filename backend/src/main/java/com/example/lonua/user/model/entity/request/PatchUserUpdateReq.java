package com.example.lonua.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUserUpdateReq {

    @Length(min=8, max=45 , message = "패스워드는 8글자 이상, 45글자 이하여야 합니다.")
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$", message = "패스워드는 대/소문자, 특수문자, 숫자를 반드시 포함한 8글자 이상이어야 합니다.")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String userPassword;

    @Length(max=45, message = "주소는 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "주소", example = "서울 동작구 보라매로 87 ", required = true)
    private String userAddr;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식으로 입력하세요.")
    @ApiModelProperty(value = "휴대전화 번호", example = "010-1234-5678", required = true)
    private String userPhoneNumber;

    @Length(max=7, message = "스타일은 최대 7글자 이하여야 합니다.")
    @Pattern(regexp = "^(캐주얼|시크|댄디|스트릿|비지니스캐주얼|힙합|페미닌|오피스|스포츠)$" , message = "올바른 스타일을 입력하세요. (캐주얼 / 시크 / 댄디 / 스트릿 / 비지니스캐주얼 / 힙합 / 페미닌 / 오피스 / 스포츠 )")
    @ApiModelProperty(value = "선호 스타일", example = "캐주얼", required = true)
    private String preferStyle;

    @Range(min = 1,max = 3, message = "상체 유형은 1 ~ 3까지만 입력 가능합니다.")
    @ApiModelProperty(value = "상체 유형(1 ~ 3)", example = "1", required = true)
    private Integer upperType;

    @Range(min = 1,max = 3, message = "하체 유형은 1 ~ 3까지만 입력 가능합니다.")
    @ApiModelProperty(value = "하체 유형(1 ~ 3)", example = "1", required = true)
    private Integer lowerType;
}
