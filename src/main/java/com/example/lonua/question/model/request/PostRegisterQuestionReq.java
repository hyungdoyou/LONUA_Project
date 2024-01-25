package com.example.lonua.question.model.request;


import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterQuestionReq {

    @NotNull(message = "상품 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "상품 IDX는 1이상의 양수입니다.")
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;

    @NotNull(message = "질문 유형은 필수 선택 항목입니다.")
    @Length(min=2, max=7, message = "질문 유형은 2 ~ 7글자 입니다.")
    @Pattern(regexp = "^(사이즈|배송|재입고|상품 상세문의)$", message = "질문 유형은 [ 사이즈 / 배송 / 재입고 / 상품 상세문의 ] 만 가능합니다.")
    @ApiModelProperty(value = "질문 유형 ( 사이즈 / 배송 / 재입고 / 상품 상세문의 )", example = "배송", required = true)
    private String questionType;  // 사이즈, 배송, 재입고, 상품 상세문의

    @NotNull(message = "질문 제목은 필수 입력 항목입니다.")
    @Length(min=1, max=45, message = "질문 제목은 최소 1이상, 최대 45글자 이내로 작성 가능합니다.")
    @ApiModelProperty(value = "질문 제목", example = "배송 관련 문의드립니다.", required = true)
    private String questionTitle;

    @NotNull(message = "질문 내용은 필수 입력 항목입니다.")
    @Length(min=1, max=500, message = "질문 내용은 최소 1이상, 최대 500글자 이내로 작성 가능합니다.")
    @ApiModelProperty(value = "질문 내용", example = "오늘 주문했는데요, 배송이 언제 오나요?", required = true)
    private String questionContent;

    @NotNull(message = "비밀글 여부는 필수 선택 항목입니다.")
    @ApiModelProperty(value = "비밀글 여부", example = "true", required = true)
    private Boolean isSecret;

}
