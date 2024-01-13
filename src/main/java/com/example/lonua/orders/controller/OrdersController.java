package com.example.lonua.orders.controller;


import com.example.lonua.config.BaseRes;
import com.example.lonua.orders.model.request.PostCreateOrdersReq;
import com.example.lonua.orders.service.OrdersService;
import com.example.lonua.user.model.entity.User;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestBody PostCreateOrdersReq postCreateOrdersReq){

        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        BaseRes baseRes = ordersService.createOrder(user, postCreateOrdersReq);

        return ResponseEntity.ok().body(baseRes);
    }

    // ---------------------카카오페이 결제를 통한 주문-----------------------------
//    @RequestMapping(method = RequestMethod.POST, value = "/create")
//    public ResponseEntity create(@RequestBody PostCreateOrdersReq postCreateOrdersReq) throws IamportResponseException, IOException {
//
//        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        BaseRes baseRes = ordersService.paymentValidation(user, postCreateOrdersReq);
//
//        return ResponseEntity.ok().body(baseRes);
//    }
    // --------------------------------------------------------------------------

//    @RequestMapping(method = RequestMethod.GET, value = "/list")
//    public ResponseEntity list() {
//        return ResponseEntity.ok().body(ordersService.list());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/read")
//    public ResponseEntity read(Integer ordersIdx) {
//
//        return ResponseEntity.ok().body(ordersService.read(ordersIdx));
//    }
}
