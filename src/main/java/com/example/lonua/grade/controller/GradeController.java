package com.example.lonua.grade.controller;


import com.example.lonua.grade.model.PostCreateReq;
import com.example.lonua.grade.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(PostCreateReq postCreateReq) {
        gradeService.create(postCreateReq);

        return ResponseEntity.ok().body("회원등급 생성 완료");
    }
}
