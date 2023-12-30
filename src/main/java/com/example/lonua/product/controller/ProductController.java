package com.example.lonua.product.controller;

import com.example.lonua.product.model.request.PostRegisterProductReq;
import com.example.lonua.product.model.response.PostRegisterProductRes;
import com.example.lonua.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity register(PostRegisterProductReq postRegisterProductReq) {
        PostRegisterProductRes postRegisterProductRes = productService.register(postRegisterProductReq);

        return ResponseEntity.ok().body(postRegisterProductRes);
    }
}
