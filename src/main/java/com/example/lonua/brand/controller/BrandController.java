package com.example.lonua.brand.controller;

import com.example.lonua.brand.model.request.PostRegisterBrandReq;
import com.example.lonua.brand.model.response.PostRegisterBrandRes;
import com.example.lonua.brand.service.BrandService;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(PostRegisterBrandReq postRegisterBrandReq) {
        PostRegisterBrandRes postRegisterBrandRes = brandService.register(postRegisterBrandReq);

        return ResponseEntity.ok().body(postRegisterBrandRes);
    }
}
