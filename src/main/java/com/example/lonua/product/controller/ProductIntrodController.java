package com.example.lonua.product.controller;

import com.example.lonua.product.model.request.PostRegisterProductIntrodReq;
import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.model.response.PostRegisterProductRes;
import com.example.lonua.product.service.ProductIntrodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productIntrod")
public class ProductIntrodController {
    private final ProductIntrodService productIntrodService;

    public ProductIntrodController(ProductIntrodService productIntrodService) {
        this.productIntrodService = productIntrodService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(PostRegisterProductIntrodReq postRegisterProductIntrodReq) {
        productIntrodService.register(postRegisterProductIntrodReq);

        return ResponseEntity.ok().body("상품 설명 이미지 등록 완료");
    }
}
