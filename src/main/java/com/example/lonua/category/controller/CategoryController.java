package com.example.lonua.category.controller;

import com.example.lonua.category.model.request.PostCategoryReq;
import com.example.lonua.category.service.CategoryService;
import com.example.lonua.config.BaseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(@RequestBody PostCategoryReq postCategoryReq) {
        BaseRes baseRes = categoryService.register(postCategoryReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(@RequestBody PostCategoryReq postCategoryReq) {
        BaseRes baseRes = categoryService.update(postCategoryReq);

        return ResponseEntity.ok().body(baseRes);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{idx}")
    public ResponseEntity delete(@PathVariable Integer idx) {
        BaseRes baseRes = categoryService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
