package com.example.lonua.branch.controller;

import com.example.lonua.branch.model.request.PostRegisterReq;
import com.example.lonua.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branch")
public class BranchController {
    private final BranchService branchService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity registerBranch(PostRegisterReq request) {
        return ResponseEntity.ok().body(branchService.create(request));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    ResponseEntity listBranch() {
        return ResponseEntity.ok().body(branchService.list());
    }
}
