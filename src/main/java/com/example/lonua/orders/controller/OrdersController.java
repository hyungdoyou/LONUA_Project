package com.example.lonua.orders.controller;


import com.example.lonua.orders.model.request.PostRegisterOrdersReq;
import com.example.lonua.orders.model.response.PostRegisterOrdersRes;
import com.example.lonua.orders.service.OrdersService;
import com.example.lonua.user.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(PostRegisterOrdersReq postRegisterOrdersReq) {

        User user = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ordersService.register(user.getUserIdx(), postRegisterOrdersReq);

        return ResponseEntity.ok().body("상품 주문이 완료되었습니다.");
    }
}
