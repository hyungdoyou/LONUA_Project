package com.example.lonua.style.controller;

import com.example.lonua.style.model.PostRegReq;
import com.example.lonua.style.service.StyleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/style")
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody PostRegReq postRegReq) {
        styleService.register(postRegReq);

        return ResponseEntity.ok().body("스타일 등록 완료");
    }
}
