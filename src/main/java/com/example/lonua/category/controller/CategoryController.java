package com.example.lonua.category.controller;

import com.example.lonua.category.model.PostRegReq;
import com.example.lonua.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(PostRegReq postRegReq) {
        categoryService.register(postRegReq);

        return ResponseEntity.ok().body("카테고리 등록 완료");
    }
}
