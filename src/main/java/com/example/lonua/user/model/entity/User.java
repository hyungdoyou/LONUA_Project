package com.example.lonua.user.model.entity;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.like.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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

    @Column(nullable = false, length = 200)
    private String userPw;

    @Column(nullable = false)
    private String authority;

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

    @Column(length = 10)
    private String preferStyle;

    @Column(nullable = false)
    private Integer upperType;

    @Column(nullable = false)
    private Integer lowerType;

    @Column(nullable = false)
    private Integer userMileage;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getUsername(){
        return userId;
    }

    @Override
    public String getPassword(){
        return userPw;
    }


}
