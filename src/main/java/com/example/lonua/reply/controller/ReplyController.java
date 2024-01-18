package com.example.lonua.reply.controller;


import com.example.lonua.common.BaseRes;
import com.example.lonua.reply.model.request.GetReplyListReq;
import com.example.lonua.reply.model.request.PostReplyRegisterReq;
import com.example.lonua.reply.model.request.PatchReplyUpdateReq;
import com.example.lonua.reply.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
@Api(value="답변 컨트롤러 v1", tags="답변 API")
public class ReplyController {

    private final ReplyService replyService;

    @ApiOperation(value = "답변 등록", response = BaseRes.class, notes = "판매자가 회원의 질문글에 답변을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity replyRegister(@RequestBody @Valid PostReplyRegisterReq request) {
        return ResponseEntity.ok().body(replyService.create(request));
    }

    @ApiOperation(value = "답변 수정", response = BaseRes.class, notes = "판매자가 답변글을 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity replyUpdate(@RequestBody @Valid PatchReplyUpdateReq request) {
        return ResponseEntity.ok().body(replyService.update(request));
    }

    @ApiOperation(value = "답변 조회", response = BaseRes.class, notes = "판매자의 모든 답변글을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity replyList(@Valid GetReplyListReq request) {
        return ResponseEntity.ok().body(replyService.list(request));
    }
}























