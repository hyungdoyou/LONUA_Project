package com.example.lonua.category.controller;

import com.example.lonua.category.model.request.PostCategoryReq;
import com.example.lonua.category.service.CategoryService;
import com.example.lonua.config.BaseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity delete(Integer idx) {
        BaseRes baseRes = categoryService.delete(idx);

        return ResponseEntity.ok().body(baseRes);
    }

    // 카테고리별 상품 리스트 출력
    @RequestMapping(method = RequestMethod.GET, value = "/{idx}")
    public ResponseEntity getProductList(Integer idx) {
        BaseRes baseRes = categoryService.getProductList(idx);

        return ResponseEntity.ok().body(baseRes);
    }
}
