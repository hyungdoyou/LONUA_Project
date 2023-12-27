package com.example.lonua.user.controller;

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
}
