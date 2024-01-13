package com.example.lonua.cart.controller;



import com.example.lonua.cart.model.request.PostRegisterReq;
import com.example.lonua.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    ResponseEntity registerCart(PostRegisterReq request) {
        return ResponseEntity.ok().body(cartService.create(request));
    }
}
