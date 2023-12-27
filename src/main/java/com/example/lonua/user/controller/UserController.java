package com.example.lonua.user.controller;

import com.example.lonua.user.model.PostLoginReq;
import com.example.lonua.user.model.PostSignUpReq;
import com.example.lonua.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup(PostSignUpReq postSignUpReq) {
        userService.signup(postSignUpReq);

        return ResponseEntity.ok().body("회원가입 성공");
    }

    // 로그인(세션 기반 로그인)
    @RequestMapping(method = RequestMethod.GET, value = "/mypage")
    public ResponseEntity mypage() {

        return ResponseEntity.ok().body("마이 페이지입니다.");
    }
}
