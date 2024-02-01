package com.example.lonua.likes.controller;


import com.example.lonua.common.BaseRes;
import com.example.lonua.likes.model.request.PostCancelLikesReq;
import com.example.lonua.likes.service.LikesService;
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
@RequestMapping("/likes")
@CrossOrigin("*")
@Api(value="좋아요 컨트롤러 v1", tags="좋아요 API")
public class LikesController {

    private final LikesService likesService;

    @ApiOperation(value = "좋아요 등록", response = BaseRes.class, notes = "회원이 원하는 상품에 좋아요를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/{productIdx}")
    public ResponseEntity createLikes(@PathVariable @NotNull @Positive Integer productIdx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        try {
            return ResponseEntity.ok().body(likesService.createLikes(user, productIdx));
        } catch (Exception e) {
            return ResponseEntity.ok().body("동시성 에러 발생");
        }
    }

    @ApiOperation(value = "좋아요 목록 조회", response = BaseRes.class, notes = "회원이 좋아요를 등록한 상품의 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = likesService.list(user);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "좋아요 취소", response = BaseRes.class, notes = "회원이 등록한 좋아요를 취소한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/cancel/{productIdx}")
    public ResponseEntity in(@PathVariable @NotNull @Positive Integer productIdx) {
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = likesService.cancle(productIdx, user);

        return ResponseEntity.ok().body(baseRes);
    }
}
