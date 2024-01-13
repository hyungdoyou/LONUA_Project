package com.example.lonua.coupon.controller;

import com.example.lonua.coupon.model.request.DeleteRemoveReq;
import com.example.lonua.coupon.model.request.GetReadReq;
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

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    ResponseEntity listCoupon() {
        return ResponseEntity.ok().body(couponService.list());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read")
    ResponseEntity readCoupon(GetReadReq request) {
        return ResponseEntity.ok().body(couponService.read(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    ResponseEntity deleteCoupon(DeleteRemoveReq request) {
        return ResponseEntity.ok().body(couponService.delete(request));
    }


}
