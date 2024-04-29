package com.example.lonua.coupon.controller;

import com.example.lonua.common.BaseRes;
import com.example.lonua.coupon.model.request.DeleteCouponRemoveReq;
import com.example.lonua.coupon.model.request.PostCouponRegisterReq;
import com.example.lonua.coupon.service.CouponService;
import com.example.lonua.user.model.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
@CrossOrigin("*")
@Api(value="쿠폰 컨트롤러 v1", tags="쿠폰 API")
public class CouponController {

    private final CouponService couponService;


    @ApiOperation(value = "쿠폰 등록", response = BaseRes.class, notes = "관리자가 쿠폰을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity registerCoupon(@RequestBody @Valid PostCouponRegisterReq request) {

        return ResponseEntity.ok().body(couponService.create(request));
    }

    @ApiOperation(value = "쿠폰 모두 조회", response = BaseRes.class, notes = "회원이 쿠폰을 모두 조회 한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    ResponseEntity listCoupon() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok().body(couponService.list(user));
    }


    @ApiOperation(value = "쿠폰 적용", response = BaseRes.class, notes = "회원이 쿠폰을 적용 한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/apply/{couponIdx}")
    ResponseEntity apply(@PathVariable @NotNull @Positive Integer couponIdx) {
        return ResponseEntity.ok().body(couponService.apply(couponIdx));
    }


//    @ApiOperation(value = "쿠폰 하나 가져오기")
//    @RequestMapping(method = RequestMethod.GET, value = "/read")
//    ResponseEntity readCoupon(@RequestBody @Valid GetReadReq request) {
//        return ResponseEntity.ok().body(couponService.read(request));
//    }

    @ApiOperation(value = "쿠폰 삭제", response = BaseRes.class, notes = "관리자가 쿠폰을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    ResponseEntity deleteCoupon(@RequestBody @Valid DeleteCouponRemoveReq request) {
        return ResponseEntity.ok().body(couponService.delete(request));
    }


}
