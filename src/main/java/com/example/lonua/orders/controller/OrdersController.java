package com.example.lonua.orders.controller;


import com.example.lonua.common.BaseRes;
import com.example.lonua.orders.model.request.PatchUpdateOrdersReq;
import com.example.lonua.orders.model.request.PostCreateOrdersReq;
import com.example.lonua.orders.service.OrdersService;
import com.example.lonua.user.model.entity.User;
import com.siot.IamportRestClient.exception.IamportResponseException;
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
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@CrossOrigin("*")
@Api(value="주문 컨트롤러 v1", tags="주문 API")
public class OrdersController {

    private final OrdersService ordersService;

//    @ApiOperation(value = "주문 생성", response = BaseRes.class, notes = "회원이 상품을 주문한다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
//    @RequestMapping(method = RequestMethod.POST, value = "/create")
//    public ResponseEntity create(@RequestBody @Valid PostCreateOrdersReq postCreateOrdersReq){
//
//        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        BaseRes baseRes = ordersService.createOrder(user, postCreateOrdersReq);
//
//        return ResponseEntity.ok().body(baseRes);
//    }

    @ApiOperation(value = "주문 목록 조회", response = BaseRes.class, notes = "회원이 주문한 상품의 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size){
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = ordersService.list(user, page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "특정 주문 내역 세부 조회", response = BaseRes.class, notes = "회원이 특정 주문 내역의 세부정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/read/{ordersIdx}/{productIdx}")
    public ResponseEntity read(@PathVariable @NotNull @Positive Integer ordersIdx, @PathVariable @NotNull @Positive Integer productIdx){
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = ordersService.read(user, ordersIdx, productIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "주문 상태 수정", response = BaseRes.class, notes = "브랜드(판매자)가 주문 상태를 수정한다. ( 주문 접수 / 배송 전 / 배송 중 / 배송 완료 )")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity updateStatus(@RequestBody @Valid PatchUpdateOrdersReq patchUpdateOrdersReq){

        BaseRes baseRes = ordersService.updateStatus(patchUpdateOrdersReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "주문 취소", response = BaseRes.class, notes = "회원이 주문을 취소한다. (상품 배송 전)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/cancle/{ordersIdx}")
    public ResponseEntity cancle(@PathVariable @NotNull @Positive Integer ordersIdx) {
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = ordersService.cancle(ordersIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    // ---------------------카카오페이 결제를 통한 주문-----------------------------
    @RequestMapping(method = RequestMethod.POST, value = "/kakaopay")
    public ResponseEntity create(@RequestBody PostCreateOrdersReq postCreateOrdersReq) throws IamportResponseException, IOException {

        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = ordersService.paymentValidation(user, postCreateOrdersReq);

        return ResponseEntity.ok().body(baseRes);
    }
    // --------------------------------------------------------------------------
}
