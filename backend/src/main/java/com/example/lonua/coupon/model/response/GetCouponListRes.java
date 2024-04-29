package com.example.lonua.coupon.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCouponListRes {
    private Integer couponIdx;
    private String couponName;
    private Integer couponDiscountRate;
    private String receivedDate;
    private Long couponExpirationDate;
    private Boolean status;
    private Integer userIdx;
}
