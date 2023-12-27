package com.example.lonua.brand.controller;

import com.example.lonua.brand.model.PostBrandReq;
import com.example.lonua.brand.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(PostBrandReq postBrandReq) {
        brandService.create(postBrandReq);

        return ResponseEntity.ok().body("브랜드 생성 완료");
    }
}
