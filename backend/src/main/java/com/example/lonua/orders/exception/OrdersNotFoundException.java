package com.example.lonua.orders.exception;

import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.exception.EntityNotFoundException;

public class OrdersNotFoundException extends EntityNotFoundException {
    public OrdersNotFoundException(Integer idx) {
        super(ErrorCode.ORDERS_NOT_EXISTS, String.format("OrdersIdx [ %s ] is not exists.", idx));
    }
}
