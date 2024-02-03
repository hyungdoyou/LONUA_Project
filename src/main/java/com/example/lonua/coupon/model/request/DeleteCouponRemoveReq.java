package com.example.lonua.coupon.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCouponRemoveReq {
    @NotNull
    @Min(1)
    @ApiModelProperty(value = "삭제할 쿠폰 IDX", example = "1", required = true)
    private Integer couponIdx;
}
