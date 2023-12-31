package com.example.lonua.user.service;

import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.UserException;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import com.example.lonua.user.model.entity.request.PostSignUpReq;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.response.GetListUserRes;
import com.example.lonua.user.model.entity.response.GetUserOrdersRes;
import com.example.lonua.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void signup(PostSignUpReq postSignUpReq) {
        // 중복된 ID에 대한 예외처리 추가
        Optional<User> result = userRepository.findByUserId(postSignUpReq.getUserId());

        if(result.isPresent()) {
            throw new UserException(ErrorCode.DUPLICATED_USER, String.format("Id is %s", postSignUpReq.getUserId()));
        }

        userRepository.save(User.builder()
                .grade(Grade.builder()
                        .gradeIdx(1) // 처음 회원 가입 시 회원등급은 반드시 1번 등급(실버)으로 가입
                        .build())
                .userId(postSignUpReq.getUserId())
                .userPw(passwordEncoder.encode(postSignUpReq.getUserPw()))
                .userName(postSignUpReq.getUserName())
                .userBirth(postSignUpReq.getUserBirth())
                .userGender(postSignUpReq.getUserGender())
                .userPhoneNumber(postSignUpReq.getUserPhoneNumber())
                .userEmail(postSignUpReq.getUserEmail())
                .userAddr(postSignUpReq.getUserAddr())
                .preferStyle(postSignUpReq.getPreferStyle())
                .upperType(postSignUpReq.getUpperType())
                .lowerType(postSignUpReq.getLowerType())
                .userMileage(0)
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(1)
                .build());
    }

    public List<GetListUserRes> list() {
        List<User> result = userRepository.findAll();

        List<GetListUserRes> getListUserResList = new ArrayList<>();
        for(User user : result) {
            List<GetUserOrdersRes> getUserOrdersResList = new ArrayList<>();
            List<Orders> ordersList = user.getOrdersList();

            for(Orders orders : ordersList) {
                GetUserOrdersRes getUserOrdersRes = GetUserOrdersRes.builder()
                        .ordersIdx(orders.getOrdersIdx())
                        .getReadOrdersProductRes(GetReadOrdersProductRes.builder()
                                .productIdx(orders.getProduct().getProductIdx())
                                .brandName(orders.getProduct().getBrand().getBrandName())
                                .productName(orders.getProduct().getProductName())
                                .price(orders.getProduct().getPrice())
                                .build())
                        .build();

                getUserOrdersResList.add(getUserOrdersRes);
            }

            GetListUserRes getListUserRes = GetListUserRes.builder()
                    .userIdx(user.getUserIdx())
                    .userId(user.getUserId())
                    .userName(user.getUsername())
                    .userBirth(user.getUserBirth())
                    .userGender(user.getUserGender())
                    .userPhoneNumber(user.getUserPhoneNumber())
                    .userEmail(user.getUserEmail())
                    .preferStyle(user.getPreferStyle())
                    .upperType(user.getUpperType())
                    .lowerType(user.getLowerType())
                    .gradeType(user.getGrade().getGradeType())
                    .getUserOrdersResList(getUserOrdersResList)
                    .build();

            getListUserResList.add(getListUserRes);
        }

        return getListUserResList;
    }

    public GetListUserRes read(String userId) {
        Optional<User> result = userRepository.findByUserId(userId);
        User user = result.get();

        List<GetUserOrdersRes> getUserOrdersResList = new ArrayList<>();
        List<Orders> ordersList = user.getOrdersList();

        for(Orders orders : ordersList) {
            GetUserOrdersRes getUserOrdersRes = GetUserOrdersRes.builder()
                    .ordersIdx(orders.getOrdersIdx())
                    .getReadOrdersProductRes(GetReadOrdersProductRes.builder()
                            .productIdx(orders.getProduct().getProductIdx())
                            .brandName(orders.getProduct().getBrand().getBrandName())
                            .productName(orders.getProduct().getProductName())
                            .price(orders.getProduct().getPrice())
                            .build())
                    .build();
            getUserOrdersResList.add(getUserOrdersRes);
        }

        GetListUserRes getListUserRes = GetListUserRes.builder()
                .userIdx(user.getUserIdx())
                .userId(user.getUserId())
                .userName(user.getUsername())
                .userBirth(user.getUserBirth())
                .userGender(user.getUserGender())
                .userPhoneNumber(user.getUserPhoneNumber())
                .userEmail(user.getUserEmail())
                .userAddr(user.getUserAddr())
                .preferStyle(user.getPreferStyle())
                .upperType(user.getUpperType())
                .lowerType(user.getLowerType())
                .gradeType(user.getGrade().getGradeType())
                .getUserOrdersResList(getUserOrdersResList)
                .build();

        return getListUserRes;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByUserId(username);
        User user = null;
        if(result.isPresent()) {
            user = result.get();
        }
        return user;
    }

}
