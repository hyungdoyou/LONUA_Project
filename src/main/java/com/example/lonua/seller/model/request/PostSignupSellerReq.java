package com.example.lonua.seller.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupSellerReq {

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    @Length(max=45, message = "이메일은 최대 45글자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 이메일 형식( test@example.com )으로 입력해주세요.")
    @ApiModelProperty(value = "이메일", example = "test@example.com", required = true)
    private String sellerEmail;

    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    @Length(min=8, max=45 , message = "패스워드는 8글자 이상, 45글자 이하여야 합니다.")
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\\\",.<>/?]).{8,}$", message = "패스워드는 대/소문자, 특수문자, 숫자를 반드시 포함한 8글자 이상이어야 합니다.")
    @ApiModelProperty(value = "패스워드(최소 길이 : 8글자 / 적어도 한개의 대문자, 소문자, 특수문자, 숫자를 포함)", example = "Qwer1234@", required = true)
    private String sellerPassword;

    @NotNull(message = "브랜드 Idx는 필수 입력 항목입니다.")
    @Min(value=1, message = "브랜드 IDX는 최소 1 이상의 숫자여야 합니다.")
    @ApiModelProperty(value = "본인 브랜드 IDX", example = "1", required = true)
    private Integer brandIdx;

    @NotNull(message = "브랜드 고유키는 필수 입력 항목입니다.")
    @Length(max=45, message = "브랜드 고유키는 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "본인 브랜드 고유키", example = "uniqueKey", required = true)
    private String brandUniqueKey;

    @NotNull(message = "판매자 이름은 필수 입력 항목입니다.")
    @Length(max=45, message = "판매자 이름은 최대 45글자 이하여야 합니다.")
    @ApiModelProperty(value = "판매자 이름", example = "[브랜드명] 담당자", required = true)
    private String sellerName;
}
