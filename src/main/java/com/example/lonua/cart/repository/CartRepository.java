package com.example.lonua.cart.repository;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.repository.querydsl.CartRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CartRepository extends JpaRepository<Cart, Integer>, CartRepositoryCustom {

    @Modifying
    @Query("DELETE FROM Cart c  WHERE c.user.userIdx = :userIdx")
    void deleteAllByUserIdx(Integer userIdx);

    public Integer deleteByCartIdx(Integer idx);
}
