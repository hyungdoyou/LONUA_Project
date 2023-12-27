package com.example.lonua.user.model.entity;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.like.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userIdx;

    // 회원등급 엔티티 관계 형성
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Grade_idx")
    private Grade grade;

    @OneToMany(mappedBy = "user")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Coupon> couponList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Orders> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false, length = 45, unique = true)
    private String userId;

    @Column(nullable = false, length = 45)
    private String userPw;

    @Column(nullable = false, length = 45)
    private String userName;

    @Column(nullable = false, length = 45)
    private String userBirth;

    @Column(nullable = false, length = 1)
    private String userGender;

    @Column(nullable = false, length = 45, unique = true)
    private String userPhoneNumber;

    @Column(nullable = false, length = 45, unique = true)
    private String userEmail;

    @Column(nullable = false, length = 45)
    private String userAddr;

    private Integer preferStyle;

    @Column(nullable = false)
    private Integer upperType;

    @Column(nullable = false)
    private Integer lowerType;

    private Integer userMileage;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false)
    private String status;

}
