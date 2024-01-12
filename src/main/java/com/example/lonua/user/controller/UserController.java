package com.example.lonua.user.controller;

import com.example.lonua.config.BaseRes;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.request.GetEmailVerifyReq;
import com.example.lonua.user.model.entity.request.PostSignUpReq;
import com.example.lonua.user.model.entity.request.PostUserLoginReq;
import com.example.lonua.user.service.EmailVerifyService;
import com.example.lonua.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailVerifyService emailVerifyService;

    // 회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(PostSignUpReq postSignUpReq) {
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
    public ResponseEntity login(PostUserLoginReq postUserLoginReq) {
        return ResponseEntity.ok().body(userService.login(postUserLoginReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        return ResponseEntity.ok().body(userService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.read(user.getUserEmail()));
    }
}
