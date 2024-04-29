package com.example.lonua.coupon.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCouponRegisterReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "쿠폰을 받을 사람의 Idx", example = "1", required = true)
    private Integer userIdx;

    @NotNull
    @Pattern(regexp = "^.{1,20}$", message = "20글자 이하의 문자열을 입력하세요.")
    @ApiModelProperty(value = "발급할 쿠폰의 이름", example = "신규회원가입쿠폰", required = true)
    private String couponName;

    @NotNull
    @Range(min = 5,max= 100)
    @ApiModelProperty(value = "발급할 쿠폰의 할인율", example = "50", required = true)
    private Integer couponDiscountRate;

}
