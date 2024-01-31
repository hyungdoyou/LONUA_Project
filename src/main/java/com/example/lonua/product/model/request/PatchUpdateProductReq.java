package com.example.lonua.product.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateProductReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;

    @Length(min=1, max=45)
    @ApiModelProperty(value = "상품명(45자 이하)", example = "하프 집업 카라넥 케이블 니트 [카키]", required = true)
    private String productName;

    @Min(value = 0)
    @ApiModelProperty(value = "수량", example = "100", required = true)
    private Integer quantity;

    @Range(min = 100,max = 400000000) // 4억 이하로만 해주세요.
    @ApiModelProperty(value = "원 가격", example = "1000", required = true)
    private Integer price;

    @Range(min = 100,max = 400000000) // 4억 이하로만 해주세요.
    @ApiModelProperty(value = "할인 가격", example = "1000", required = true)
    private Integer salePrice;

}
