package com.example.lonua.likes.controller;


import com.example.lonua.likes.service.LikesService;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {
    private final LikesService likesService;

    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity createLikes(@AuthenticationPrincipal User user, @PathVariable Integer idx) {
        try {
            likesService.createLikes(user, idx);
        } catch (Exception e) {
            System.out.println("동시성 에러 발생");
        }
         return ResponseEntity.ok().body("ok");
    }
}
