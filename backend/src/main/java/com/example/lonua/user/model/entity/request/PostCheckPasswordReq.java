package com.example.lonua.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCheckPasswordReq {
    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    @Length(min=8, max=45, message = "패스워드는 8글자 이상, 45글자 이하여야 합니다.")
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$", message = "패스워드는 대/소문자, 특수문자, 숫자를 반드시 포함한 8글자 이상이어야 합니다.")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String password;
}
