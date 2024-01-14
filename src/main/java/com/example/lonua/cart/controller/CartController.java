package com.example.lonua.cart.controller;



import com.example.lonua.cart.model.request.DeleteRemoveReq;
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

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    ResponseEntity listCart(Integer page, Integer size) {
        return ResponseEntity.ok().body(cartService.list(page, size));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    ResponseEntity deleteCart(DeleteRemoveReq request) {
        return ResponseEntity.ok().body(cartService.delete(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAll")
    ResponseEntity deleteCart() {
        return ResponseEntity.ok().body(cartService.deleteAll());
    }
}
