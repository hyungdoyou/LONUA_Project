package com.example.lonua.user.controller;

import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.request.PostSignUpReq;
import com.example.lonua.user.model.entity.request.PostUserLoginReq;
import com.example.lonua.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
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

    // 로그인
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody PostUserLoginReq postUserLoginReq) {
        return ResponseEntity.ok().body(userService.login(postUserLoginReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        return ResponseEntity.ok().body(userService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return ResponseEntity.ok().body(userService.read(user.getUserId()));
    }
}
