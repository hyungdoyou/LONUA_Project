package com.example.lonua.question.controller;


import com.example.lonua.common.BaseRes;
import com.example.lonua.question.model.request.PatchUpdateQuestionReq;
import com.example.lonua.question.model.request.PostRegisterQuestionReq;
import com.example.lonua.question.service.QuestionService;
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
@RequestMapping("/question")
@CrossOrigin("*")
@Api(value="질문 컨트롤러 v1", tags="질문 API")
public class QuestionController {

    private final QuestionService questionService;

    @ApiOperation(value = "질문 등록", response = BaseRes.class, notes = "회원이 상품에 대한 질문을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody @Valid PostRegisterQuestionReq postRegisterQuestionReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = questionService.register(user, postRegisterQuestionReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "질문 목록 조회", response = BaseRes.class, notes = "회원이 본인이 작성한 질문의 목록을 조회한다. (페이지 별)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = questionService.list(user, page, size);

        return ResponseEntity.ok().body(baseRes);
    }
    @ApiOperation(value = "상품 상세 페이지 질문 목록 조회", response = BaseRes.class, notes = "상품 상세 페이지에서 질문 목록을 조회한다. (페이지 별)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/productQnA/list/{productIdx}/{page}/{size}")
    public ResponseEntity listproductQnA(@PathVariable @NotNull @Positive Integer productIdx, @PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        BaseRes baseRes = questionService.listproductQnA(productIdx, page, size);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "질문 수정", response = BaseRes.class, notes = "회원이 작성한 질문을 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateQuestionReq patchUpdateQuestionReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = questionService.update(user, patchUpdateQuestionReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "질문 삭제", response = BaseRes.class, notes = "회원이 작성한 질문을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{questionIdx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer questionIdx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = questionService.delete(questionIdx, user);

        return ResponseEntity.ok().body(baseRes);
    }

}
