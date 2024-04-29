package com.example.lonua.seller.controller;

import com.example.lonua.seller.model.request.PatchUpdateSellerReq;
import com.example.lonua.seller.model.request.PostLoginSellerReq;
import com.example.lonua.seller.model.request.PostSignupSellerReq;
import com.example.lonua.seller.service.SellerService;
import com.example.lonua.common.BaseRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
@CrossOrigin("*")
@Api(value="판매자 계정 컨트롤러 v1", tags="판매자 계정 API")
public class SellerController {

    private final SellerService sellerService;


    @ApiOperation(value = "판매자 회원 가입", response = BaseRes.class, notes = "판매자가 판매자 정보를 입력하여 회원 가입을 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@RequestBody @Valid PostSignupSellerReq postSignupSellerReq) {
        BaseRes baseRes = sellerService.signup(postSignupSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "판매자 로그인", response = BaseRes.class, notes = "판매자가 로그인을 시도한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody @Valid PostLoginSellerReq postLoginSellerReq) {
        BaseRes baseRes = sellerService.login(postLoginSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "판매자 계정 목록 조회", response = BaseRes.class, notes = "관리자가 판매자의 계정 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        BaseRes baseRes = sellerService.list(page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "판매자 계정 정보 수정", response = BaseRes.class, notes = "판매자가 계정의 정보를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateSellerReq patchUpdateSellerReq) {
        BaseRes baseRes = sellerService.update(patchUpdateSellerReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "판매자 계정 삭제", response = BaseRes.class, notes = "판매자 계정을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes = sellerService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
