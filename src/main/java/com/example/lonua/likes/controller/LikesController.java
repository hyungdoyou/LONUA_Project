package com.example.lonua.likes.controller;


import com.example.lonua.config.BaseRes;
import com.example.lonua.likes.model.request.PostCancelLikesReq;
import com.example.lonua.likes.service.LikesService;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity createLikes(@PathVariable Integer idx) {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        try {
            likesService.createLikes(user, idx);
        } catch (Exception e) {
            System.out.println("동시성 에러 발생");
        }
        return ResponseEntity.ok().body(BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("요청 성공")
                        .result("좋아요를 추가하였습니다.")
                .build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = likesService.list(user);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cancel")
    public ResponseEntity in(@RequestBody PostCancelLikesReq postCancelLikesReq) {
        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = likesService.cancle(user, postCancelLikesReq);

        return ResponseEntity.ok().body(baseRes);
    }
}
