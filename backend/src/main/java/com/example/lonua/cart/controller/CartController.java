package com.example.lonua.cart.controller;



import com.example.lonua.cart.model.request.DeleteCartRemoveReq;
import com.example.lonua.cart.model.request.PostCartRegisterReq;
import com.example.lonua.cart.service.CartService;
import com.example.lonua.common.BaseRes;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@CrossOrigin("*")
@Api(value="장바구니 컨트롤러 v1", tags="장바구니 API")
public class CartController {
    private final CartService cartService;


    @ApiOperation(value = "장바구니 추가", response = BaseRes.class, notes = "회원이 장바구니에 물건을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity registerCart(@RequestBody PostCartRegisterReq request) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok().body(cartService.create(user, request));
    }

    @ApiOperation(value = "장바구니 조회", response = BaseRes.class, notes = "회원이 장바구니에 있는 모든 물건을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    ResponseEntity listCart(@PathVariable Integer page,@PathVariable Integer size) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok().body(cartService.list(user, page, size));
        //return ResponseEntity.ok().body(cartService.list(page, size));

    }

    @ApiOperation(value = "장바구니 단일 삭제", response = BaseRes.class, notes = "회원이 장바구니에 물건을 단일 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{cartIdx}")
    ResponseEntity deleteCart(@PathVariable @NotNull @Positive Integer cartIdx) {

        return ResponseEntity.ok().body(cartService.delete(cartIdx));
    }


    @ApiOperation(value = "장바구니 모두 삭제", response = BaseRes.class, notes = "회원이 장바구니에 모든 물건을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAll")
    ResponseEntity deleteAllCart() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok().body(cartService.deleteAll(user));
    }
}
