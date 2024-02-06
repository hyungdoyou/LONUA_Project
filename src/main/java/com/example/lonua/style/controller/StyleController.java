package com.example.lonua.style.controller;

import com.example.lonua.common.BaseRes;
import com.example.lonua.style.model.request.PatchUpdateStyleReq;
import com.example.lonua.style.model.request.PostRegisterStyleReq;
import com.example.lonua.style.service.StyleService;
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
@RequestMapping("/style")
@CrossOrigin("*")
@Api(value="스타일 컨트롤러 v1", tags="스타일 API")
public class StyleController {

    private final StyleService styleService;

    @ApiOperation(value = "스타일 등록", response = BaseRes.class, notes = "관리자가 스타일을 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody @Valid PostRegisterStyleReq postRegisterStyleReq) {

        BaseRes baseRes = styleService.register(postRegisterStyleReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "스타일 전체 목록 조회", response = BaseRes.class, notes = "관리자가 스타일 전체 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
        BaseRes baseRes = styleService.list();

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "스타일 수정", response = BaseRes.class, notes = "관리자가 등록된 스타일을 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateStyleReq patchUpdateStyleReq) {
        BaseRes baseRes = styleService.update(patchUpdateStyleReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "스타일 삭제", response = BaseRes.class, notes = "관리자가 등록된 스타일을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull(message = "스타일 IDX는 필수 입력 항목입니다.") @Positive(message = "스타일 IDX는 1이상의 양수입니다.") Integer idx) {
        BaseRes baseRes = styleService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
