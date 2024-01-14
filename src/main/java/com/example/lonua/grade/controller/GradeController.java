package com.example.lonua.grade.controller;


import com.example.lonua.grade.model.request.GetReadReq;
import com.example.lonua.grade.model.request.PatchUpdateReq;
import com.example.lonua.grade.model.request.PostCreateReq;
import com.example.lonua.grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity registerGrade(@RequestBody PostCreateReq postCreateReq) {
        return ResponseEntity.ok().body(gradeService.create(postCreateReq));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    ResponseEntity listGrade() {
        return ResponseEntity.ok().body(gradeService.list());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read")
    ResponseEntity readGrade(@RequestBody GetReadReq request) {
        return ResponseEntity.ok().body(gradeService.read(request));
    }
    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    ResponseEntity updateGrade(@RequestBody PatchUpdateReq request) {
        return ResponseEntity.ok().body(gradeService.update(request));
    }
//    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
//    ResponseEntity deleteBranch(DeleteRemoveReq request) {
//        return ResponseEntity.ok().body(branchService.delete(request));
//    }
}
