package com.example.lonua.product.controller;

import com.example.lonua.product.model.PostRegReq;
import com.example.lonua.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(PostRegReq postRegReq) {
        productService.register(postRegReq);

        return ResponseEntity.ok().body("상품 등록 완료");
    }
}
