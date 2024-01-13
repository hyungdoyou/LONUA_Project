package com.example.lonua.coupon.controller;

import com.example.lonua.coupon.model.request.PostRegisterReq;
import com.example.lonua.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity registerCoupon(PostRegisterReq request) {
        return ResponseEntity.ok().body(couponService.create(request));
    }
}
