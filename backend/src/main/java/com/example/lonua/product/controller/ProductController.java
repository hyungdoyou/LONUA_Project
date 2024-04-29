package com.example.lonua.product.controller;

import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.request.PatchUpdateProductReq;
import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.service.ProductService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Api(value="상품 컨트롤러 v1", tags="상품 API")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "상품 등록", response = BaseRes.class, notes = "브랜드(판매자)가 판매 상품을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(
            @RequestPart(value = "product") @Valid PostRegisterProductReq postRegisterProductReq,
            @RequestPart(value = "productImage") @NotNull MultipartFile[] productFiles,
            @RequestPart(value = "productIntrodImage") @NotNull MultipartFile[] productIntrodFiles
    ) {
        BaseRes baseRes = productService.register(postRegisterProductReq, productFiles, productIntrodFiles);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "상품 목록 조회", response = BaseRes.class, notes = "회원이 페이지 별 상품 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = productService.list(page, size);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "상품 세부 정보 조회", response = BaseRes.class, notes = "회원이 상품 세부 정보 조회를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity read(@PathVariable @NotNull @Positive Integer idx) {

        BaseRes baseRes = productService.read(idx);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "상품 정보 수정", response = BaseRes.class, notes = "브랜드(판매자)가 상품 정보를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateProductReq patchUpdateProductReq) {

        BaseRes baseRes = productService.update(patchUpdateProductReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "상품 삭제", response = BaseRes.class, notes = "브랜드(판매자)가 등록한 상품을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer idx) {

        BaseRes baseRes = productService.delete(idx);
        return ResponseEntity.ok().body(baseRes);
    }

    //----------------------검색 기능-------------------------
    // 1. 카테고리 별 상품 리스트 검색(최신 등록 순)
    @ApiOperation(value = "카테고리 별 상품 목록 조회", response = BaseRes.class, notes = "회원이 카테고리에 해당하는 상품의 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/categorylist/{categoryIdx}/{page}/{size}")
    public ResponseEntity categoryProductlist(@PathVariable @NotNull @Positive Integer categoryIdx, @PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = productService.categoryProductlist(categoryIdx, page, size);
        return ResponseEntity.ok().body(baseRes);
    }

        // 2. 스타일 별 상품 리스트 검색(최신 등록 순)
    @ApiOperation(value = "스타일 별 상품 목록 조회", response = BaseRes.class, notes = "회원이 스타일에 해당하는 상품의 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/stylelist/{styleIdx}/{page}/{size}")
    public ResponseEntity styleProductlist(@PathVariable @NotNull @Positive Integer styleIdx, @PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = productService.styleProductlist(styleIdx, page, size);
        return ResponseEntity.ok().body(baseRes);
    }


    // 3. 같은 상/하체 체형을 가진 사람이 많이 주문한 상품 리스트 검색(상체 - 하체 순 정렬)
    @ApiOperation(value = "회원의 신체 유형에 따른 상품 목록 조회", response = BaseRes.class, notes = "회원이 자신의 상/하체 유형과 동일한 회원이 많이 구매한 상품 순으로 상품 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/sametype/{page}/{size}")
    public ResponseEntity sameTypeProductList(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        BaseRes baseRes = productService.sameTypeProductList(user, page, size);
        return ResponseEntity.ok().body(baseRes);
    }

    // 4. 판매자가 등록한 본인 브랜드의 상품 리스트 검색
    @ApiOperation(value = "브랜드 별 상품의 목록 조회", response = BaseRes.class, notes = "회원/판매자가 브랜드 별 상품의 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/brand/{brandIdx}/{page}/{size}")
    public ResponseEntity brandProductList(@PathVariable @NotNull @Positive Integer brandIdx, @PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = productService.brandProductList(brandIdx, page, size);
        return ResponseEntity.ok().body(baseRes);
    }
}
