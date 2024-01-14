package com.example.lonua.user.controller;

import com.example.lonua.config.BaseRes;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.request.GetEmailVerifyReq;
import com.example.lonua.user.model.entity.request.PostSignUpReq;
import com.example.lonua.user.model.entity.request.PostUserLoginReq;
import com.example.lonua.user.model.entity.request.PatchUserUpdateReq;
import com.example.lonua.user.service.EmailVerifyService;
import com.example.lonua.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailVerifyService emailVerifyService;

    // 회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(@RequestBody PostSignUpReq postSignUpReq) {
        BaseRes baseRes = userService.signup(postSignUpReq);

        // 인증메일 발송
        userService.sendEmail(postSignUpReq);

        return ResponseEntity.ok().body(baseRes);
    }

    // 메일 이증
    @RequestMapping(method = RequestMethod.GET, value = "/verify")
    public ResponseEntity veriify(GetEmailVerifyReq getEmailVerifyReq) {
        if (emailVerifyService.verify(getEmailVerifyReq)) {
            BaseRes baseRes = userService.updateStatus(getEmailVerifyReq.getEmail()); // 이메일 인증이 완료되면 회원의 status를 바꿔줌
            return ResponseEntity.ok().body(baseRes);
        } else {
            return ResponseEntity.ok().body(BaseRes.builder()
                            .code(400)
                            .isSuccess(false)
                            .message("메일 인증을 다시 진행해주세요.")
                            .result("인증에 실패하였습니다.")
                    .build());
        }
    }


    // 로그인
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody PostUserLoginReq postUserLoginReq) {

        BaseRes baseRes = userService.login(postUserLoginReq);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        BaseRes baseRes = userService.list();
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        BaseRes baseRes = userService.read(user.getUserEmail());

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody PatchUserUpdateReq patchUserUpdateReq) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        BaseRes baseRes = userService.update(user.getUserEmail(), patchUserUpdateReq);

        return ResponseEntity.ok().body(baseRes);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{userIdx}")
    public ResponseEntity delete(@PathVariable Integer userIdx) {

        BaseRes baseRes = userService.delete(userIdx);

        return ResponseEntity.ok().body(baseRes);
    }
}
