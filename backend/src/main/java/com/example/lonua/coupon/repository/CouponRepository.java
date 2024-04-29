package com.example.lonua.coupon.repository;

import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon>findAllByUserUserIdx(Integer userIdx);

    Optional<Coupon> findByCouponIdx(Integer couponIdx);

    Integer deleteByCouponIdx(Integer couponIdx);
}
