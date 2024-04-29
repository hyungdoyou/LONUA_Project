package com.example.lonua.coupon.model.request;

import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterReq {
    private Integer userIdx;
    private String couponName;
    private Integer couponDiscountRate;
    private Boolean status;
}
