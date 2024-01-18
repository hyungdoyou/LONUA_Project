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

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;

    @NotNull
    @Length(min=2, max=7)
    @Pattern(regexp = "^(사이즈|배송|재입고|상품 상세문의)$")
    @ApiModelProperty(value = "질문 유형 ( 사이즈 / 배송 / 재입고 / 상품 상세문의 )", example = "배송", required = true)
    private String questionType;  // 사이즈, 배송, 재입고, 상품 상세문의

    @NotNull
    @Length(min=1, max=45)
    @ApiModelProperty(value = "질문 제목", example = "배송 관련 문의드립니다.", required = true)
    private String questionTitle;

    @NotNull
    @Length(min=1, max=500)
    @ApiModelProperty(value = "질문 내용", example = "오늘 주문했는데요, 배송이 언제 오나요?", required = true)
    private String questionContent;

    @NotNull
    @ApiModelProperty(value = "비밀글 여부", example = "true", required = true)
    private Boolean isSecret;

}
