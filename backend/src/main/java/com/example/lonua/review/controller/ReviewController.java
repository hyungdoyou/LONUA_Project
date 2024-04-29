package com.example.lonua.review.controller;

import com.example.lonua.common.BaseRes;
import com.example.lonua.review.model.request.*;
import com.example.lonua.review.service.ReviewService;
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
@RequestMapping("/review")
@CrossOrigin("*")
@Api(value="리뷰 컨트롤러 v1", tags="리뷰 API")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation(value = "리뷰 등록", response = BaseRes.class, notes = "회원은 구매한 상품에 대한 리뷰를 작성할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity registReview(
            @RequestPart(value = "review") @Valid PostRegisterReviewReq postRegisterReviewReq,
            @RequestPart(value = "reviewPhoto") MultipartFile file
    ) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.registerReview(user, postRegisterReviewReq, file);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "리뷰 조회", response = BaseRes.class, notes = "회원은 상품에 대한 리뷰를 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/read/{reviewIdx}")
    public ResponseEntity readReview(@PathVariable @NotNull(message = "리뷰 IDX는 필수 입력 항목입니다.") @Positive(message = "리뷰 IDX는 1이상의 양수입니다.") Integer reviewIdx) {
        BaseRes baseRes = reviewService.readReview(reviewIdx);
        return ResponseEntity.ok().body(baseRes);
    }

        @ApiOperation(value = "회원 본인이 작성한 리뷰 목록 조회", response = BaseRes.class, notes = "회원은 본인이 작성한 전체 리뷰를 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
                User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.list(user);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "리뷰 목록 조회", response = BaseRes.class, notes = "회원은 상품에 대한 전체 리뷰를 조회할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })

    @RequestMapping(method = RequestMethod.GET, value = "/list/{productIdx}")
    public ResponseEntity listReview(@PathVariable @NotNull(message = "상품 IDX는 필수 입력 항목입니다.") @Positive(message = "상품 IDX는 1이상의 양수입니다.") Integer productIdx) {
        BaseRes baseRes = reviewService.listReview(productIdx);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "등록한 리뷰 수정", response = BaseRes.class, notes = "회원은 등록한 리뷰를 수정할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity updateReview(
            @RequestPart(value = "review") @Valid PatchUpdateReviewReq request,
            @RequestPart(value = "reviewPhoto") MultipartFile reviewFile) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = reviewService.updateReview(request, reviewFile, user);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "등록한 리뷰 삭제", response = BaseRes.class, notes = "회원은 등록한 리뷰를 삭제할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{reviewIdx}")
    public ResponseEntity deleteReview(@PathVariable @NotNull(message = "리뷰 IDX는 필수 입력 항목입니다.") @Positive(message = "리뷰 IDX는 1이상의 양수입니다.") Integer reviewIdx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes =  reviewService.deleteReview(reviewIdx, user);

        return ResponseEntity.ok().body(baseRes);
    }
}
