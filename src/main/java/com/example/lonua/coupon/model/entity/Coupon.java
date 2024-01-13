package com.example.lonua.coupon.model.entity;

import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    private String receivedDate;

    @Column(nullable = false)
    private String couponExpirationDate;

    @Column(nullable = false)
    private Boolean status;

}
