package com.example.lonua.cart.repository;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.repository.querydsl.CartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>, CartRepositoryCustom {
}
