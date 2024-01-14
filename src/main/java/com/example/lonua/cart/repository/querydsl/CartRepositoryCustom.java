package com.example.lonua.cart.repository.querydsl;

import com.example.lonua.cart.model.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartRepositoryCustom {
    public Page<Cart> findList(Pageable pageable);
}
