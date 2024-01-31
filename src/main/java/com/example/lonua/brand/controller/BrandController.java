package com.example.lonua.brand.controller;

import com.example.lonua.brand.model.request.PatchUpdateBrandReq;
import com.example.lonua.brand.model.request.PostRegisterBrandReq;
import com.example.lonua.brand.service.BrandService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
@Api(value="브랜드(판매자) 컨트롤러 v1", tags="브랜드(판매자) API")
@CrossOrigin("*")
public class BrandController {

    private final BrandService brandService;

    @ApiOperation(value = "브랜드 등록", response = BaseRes.class, notes = "관리자가 브랜드를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(
            @RequestPart(value = "brand") @Valid PostRegisterBrandReq postRegisterBrandReq,
            @RequestPart(value = "brandImage") @NotNull MultipartFile brandFile) {
        BaseRes baseRes = brandService.register(postRegisterBrandReq, brandFile);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "브랜드 목록 조회", response = BaseRes.class, notes = "관리자가 브랜드 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        BaseRes baseRes = brandService.list(page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "특정 브랜드 정보 조회", response = BaseRes.class, notes = "관리자가 특정 브랜드의 정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity read(@PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes = brandService.read(idx);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "브랜드 정보 수정", response = BaseRes.class, notes = "관리자가 브랜드 정보를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(
            @RequestPart(value = "brand") @Valid PatchUpdateBrandReq patchUpdateBrandReq,
            @RequestPart(value = "brandImage") MultipartFile brandFile) {

        BaseRes baseRes = brandService.update(patchUpdateBrandReq, brandFile);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "브랜드 삭제", response = BaseRes.class, notes = "관리자가 브랜드를 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer idx) {

        BaseRes baseRes = brandService.delete(idx);
        return ResponseEntity.ok().body(baseRes);
    }


    @ApiOperation(value = "상품 포함 전체 브랜드 조회", response = BaseRes.class, notes = "상품을 포함한 전체 브랜드를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/listall")
    public ResponseEntity listAll() {
        BaseRes baseRes = brandService.listAll();

        return ResponseEntity.ok().body(baseRes);
    }
}
