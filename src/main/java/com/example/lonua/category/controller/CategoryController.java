package com.example.lonua.category.controller;

import com.example.lonua.category.model.request.PatchUpdateCategoryReq;
import com.example.lonua.category.model.request.PostRegisterCategoryReq;
import com.example.lonua.category.service.CategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value="카테고리 컨트롤러 v1", tags="카테고리 API")
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation(value = "카테고리 등록", response = BaseRes.class, notes = "관리자가 카테고리를 등록한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody @Valid PostRegisterCategoryReq postRegisterCategoryReq) {
        BaseRes baseRes = categoryService.register(postRegisterCategoryReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "카테고리 수정", response = BaseRes.class, notes = "관리자가 카테고리를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUpdateCategoryReq patchUpdateCategoryReq) {
        BaseRes baseRes = categoryService.update(patchUpdateCategoryReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "카테고리 삭제", response = BaseRes.class, notes = "관리자가 카테고리를 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer idx) {
        BaseRes baseRes = categoryService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
