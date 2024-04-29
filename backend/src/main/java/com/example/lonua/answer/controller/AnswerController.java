package com.example.lonua.answer.controller;


import com.example.lonua.answer.model.request.PatchUpdateAnswerReq;
import com.example.lonua.answer.model.request.PostRegisterAnswerReq;
import com.example.lonua.answer.service.AnswerService;
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
@CrossOrigin("*")
@RequestMapping("/answer")
@Api(value="답변 컨트롤러 v1", tags="답변 API")
public class AnswerController {

    private final AnswerService answerService;

    @ApiOperation(value = "답변 등록", response = BaseRes.class, notes = "판매자가 회원의 질문글에 답변을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody @Valid PostRegisterAnswerReq postRegisterAnswerReq) {
        return ResponseEntity.ok().body(answerService.create(postRegisterAnswerReq));
    }

    @ApiOperation(value = "답변 수정", response = BaseRes.class, notes = "판매자가 답변글을 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateAnswerReq patchUpdateAnswerReq) {
        return ResponseEntity.ok().body(answerService.update(patchUpdateAnswerReq));
    }

    @ApiOperation(value = "답변 조회", response = BaseRes.class, notes = "판매자의 모든 답변글을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {
        return ResponseEntity.ok().body(answerService.list(page, size));
    }
}























