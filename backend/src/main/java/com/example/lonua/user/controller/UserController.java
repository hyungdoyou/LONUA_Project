package com.example.lonua.user.controller;

import com.example.lonua.common.BaseRes;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.request.*;
import com.example.lonua.user.service.EmailVerifyService;
import com.example.lonua.user.service.UserService;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value="회원 컨트롤러 v1", tags="회원 API")
public class UserController {

    private final UserService userService;
    private final EmailVerifyService emailVerifyService;


    @ApiOperation(value = "회원 가입", response = BaseRes.class, notes = "회원이 회원 정보를 입력하여 회원 가입을 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@RequestBody @Valid PostSignUpReq postSignUpReq) {
        BaseRes baseRes = userService.signup(postSignUpReq);

        // 인증메일 발송
        userService.sendEmail(postSignUpReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "이메일 인증", response = BaseRes.class, notes = "회원이 이메일 인증을 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public RedirectView verify(GetEmailVerifyReq getEmailVerifyReq) {
        if (emailVerifyService.verify(getEmailVerifyReq)) {
            BaseRes baseRes = userService.updateStatus(getEmailVerifyReq.getEmail()); // 이메일 인증이 완료되면 회원의 status를 바꿔줌
//            return ResponseEntity.ok().body(true);
            return new RedirectView("http://www.lonuamall.kro.kr/UserLogIn");
        } else {
//            return new RedirectView("http://localhost:8081/UserLogIn");
//            return ResponseEntity.ok().body(false);
            return new RedirectView("http://www.lonuamall.kro.kr/UserSignUpSuccess");
        }
    }

    @ApiOperation(value = "로그인", response = BaseRes.class, notes = "회원이 로그인을 시도한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody @Valid PostUserLoginReq postUserLoginReq) {

        BaseRes baseRes = userService.login(postUserLoginReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "회원 목록 조회", response = BaseRes.class, notes = "관리자가 전체 회원의 목록을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}/{size}")
    public ResponseEntity list(@PathVariable @NotNull @Positive Integer page, @PathVariable @NotNull @Positive Integer size) {

        BaseRes baseRes = userService.list(page, size);
        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "회원 정보 조회", response = BaseRes.class, notes = "회원이 본인의 회원 정보를 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.read(user.getUserEmail());

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "회원 정보 수정", response = BaseRes.class, notes = "회원이 본인의 회원 정보를 수정한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody @Valid PatchUserUpdateReq patchUserUpdateReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = userService.update(user.getUserEmail(), patchUserUpdateReq);

        return ResponseEntity.ok().body(baseRes);
    }


    @ApiOperation(value = "회원 삭제", response = BaseRes.class, notes = "관리자가 탈퇴한 회원의 정보를 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{userIdx}")
    public ResponseEntity delete(@PathVariable @NotNull @Positive Integer userIdx) {
        BaseRes baseRes = userService.delete(userIdx);

        return ResponseEntity.ok().body(baseRes);
    }

    @ApiOperation(value = "회원 탈퇴", response = BaseRes.class, notes = "회원이 회원 탈퇴를 진행한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BaseRes.class)) }) })
    @RequestMapping(method = RequestMethod.GET, value = "/cancel")
    public ResponseEntity cancel() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.cancel(user.getUserIdx()));
    }

        @ApiOperation(value = "회원 정보 수정 시 비밀번호 확인", response = BaseRes.class, notes = "회원이 회원 정보를 수정하기 위해서 비밀번호를 입력한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK ( 요청 성공 )", content = { @Content(mediaType = "application/json") }) })
    @RequestMapping(method = RequestMethod.POST, value = "/checkPw")
    public ResponseEntity checkPassword(@RequestBody @Valid PostCheckPasswordReq postCheckPasswordReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.checkPassword(user, postCheckPasswordReq));
    }

}
