package com.example.lonua.user.model.entity;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.likes.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.user.model.entity.request.PatchUserUpdateReq;
import io.swagger.models.auth.In;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Coupon> couponList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Orders> ordersList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();


    @Column(nullable = false, length = 45, unique = true)
    private String userEmail;

    @Column(nullable = false, length = 200)
    private String userPassword;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 10)   // 1900-01-01
    private String userBirth;

    @Column(nullable = false, length = 1)    // M or G
    private String userGender;

    @Column(nullable = false, length = 13)   // 010-0000-0000
    private String userPhoneNumber;

    @Column(nullable = false, length = 45)
    private String userAddr;

    @Column(length = 7)
    private String preferStyle;

    @Column(nullable = false)
    private Integer upperType;

    @Column(nullable = false)
    private Integer lowerType;

    @Column(nullable = false)
    private Integer userMileage;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;


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
        return userEmail;
    }

    @Override
    public String getPassword(){
        return userPassword;
    }


    public void increaseMileage(Integer mileage) {
        if(userMileage != null) {
            this.userMileage += mileage;
        }
    }
    public void update(PatchUserUpdateReq patchUserUpdateReq, String userPassword) {
        if (userPassword != null) {
            this.userPassword = userPassword;
        }
        if (patchUserUpdateReq.getUserAddr() != null) {
            this.userAddr = patchUserUpdateReq.getUserAddr();
        }
        if (patchUserUpdateReq.getUserPhoneNumber() != null) {
            this.userPhoneNumber = patchUserUpdateReq.getUserPhoneNumber();
        }
        if (patchUserUpdateReq.getPreferStyle() != null) {
            this.preferStyle = patchUserUpdateReq.getPreferStyle();
        }
        if (patchUserUpdateReq.getUpperType() != null) {
            this.upperType = patchUserUpdateReq.getUpperType();
        }
        if (patchUserUpdateReq.getLowerType() != null) {
            this.lowerType = patchUserUpdateReq.getLowerType();
        }
    }


}
