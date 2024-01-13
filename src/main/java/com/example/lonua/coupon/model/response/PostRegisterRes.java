package com.example.lonua.coupon.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterRes {

    private Integer couponIdx;
    private String couponName;
    private Integer couponDiscountRate;
    private String receivedDate;
    private String couponExpirationDate;
    private Boolean status;
    private Integer userIdx;
}
