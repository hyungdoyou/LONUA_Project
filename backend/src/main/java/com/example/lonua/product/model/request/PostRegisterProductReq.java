package com.example.lonua.product.model.request;


import com.example.lonua.product.model.response.GetProductIntrodRes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "PostRegisterProductReq", description = "상품 등록 요청 데이터")
public class PostRegisterProductReq {

    @NotNull
    @Positive
    @ApiModelProperty(value = "판매자 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer brand_idx;

    @NotNull
    @Positive
    @ApiModelProperty(value = "카테고리 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer category_idx;

    @NotNull
    @Positive
    @ApiModelProperty(value = "스타일 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer style_idx;

    @NotNull
    @Length(min=1, max=100)
    @ApiModelProperty(value = "상품명(100자 이하)", example = "하프 집업 카라넥 케이블 니트 [카키]", required = true)
    private String productName;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "수량", example = "100", required = true)
    private Integer quantity;

    @NotNull
    @Range(min = 100,max = 400000000) // 4억 이하로만 해주세요.
    @ApiModelProperty(value = "원 가격", example = "1000", required = true)
    private Integer price;

    @NotNull
    @Range(min = 100,max = 400000000) // 4억 이하로만 해주세요.
    @ApiModelProperty(value = "할인 가격", example = "1000", required = true)
    private Integer salePrice;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "어깨 너비", example = "15.0", required = true)
    private Float shoulderWidth;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "가슴 단면", example = "15.0", required = true)
    private Float chestSize;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "팔 길이", example = "15.0", required = true)
    private Float armLength;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "상의 총 길이", example = "15.0", required = true)
    private Float topLength;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "허리 둘레", example = "15.0", required = true)
    private Float waistline;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "엉덩이 둘레", example = "15.0", required = true)
    private Float hipCircumference;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "밑 단", example = "15.0", required = true)
    private Float thighCircumference;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "밑 위", example = "15.0", required = true)
    private Float crotchLength;

    @NotNull
    @Min(0)
    @ApiModelProperty(value = "허벅지 둘레", example = "15.0", required = true)
    private Float hemLength;


    @NotNull
    @Min(0)
    @ApiModelProperty(value = "하의 총 길이", example = "15.0", required = true)
    private Float totalBottomLength;
}
