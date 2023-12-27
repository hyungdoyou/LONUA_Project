package com.example.lonua.coupon.model.entity;

import com.example.lonua.user.model.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @Column(nullable = false, length = 45)
    private String couponName;

    @Column(nullable = false)
    private Integer couponDiscountRate;

    @Column(nullable = false)
    private Date receivedDate;

    @Column(nullable = false)
    private Date couponExpirationDate;

    @Column(nullable = false)
    private Integer status;

}
