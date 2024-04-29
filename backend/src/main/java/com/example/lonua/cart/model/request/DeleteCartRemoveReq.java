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
public class DeleteCartRemoveReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "카트Idx", example = "1", required = true)
    private Integer cartIdx;
}
