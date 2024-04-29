package com.example.lonua.cart.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostCartRegisterReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "장바구니에 담을 상품 idx", example = "1", required = true)
    private Integer productIdx;

}
