package com.example.lonua.question.controller;


import com.example.lonua.config.BaseRes;
import com.example.lonua.question.model.request.PostQuestionReq;
import com.example.lonua.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity registerQuestion(PostQuestionReq request) {
        BaseRes baseRes = questionService.registerQuestion(request);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity readQuestion(Integer questionIdx) {
        BaseRes baseRes = questionService.readQuestion(questionIdx);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity updateQuestion(PostQuestionReq request) {
        BaseRes baseRes = questionService.updateQuestion(request);
        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity deleteQuestion(Integer questionIdx) {
        BaseRes baseRes = questionService.deleteQuestion(questionIdx);
        return ResponseEntity.ok().body(baseRes);
    }

}
