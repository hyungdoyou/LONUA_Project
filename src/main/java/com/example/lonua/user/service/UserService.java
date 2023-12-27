package com.example.lonua.user.service;

import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.UserException;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.grade.repository.GradeRepository;
import com.example.lonua.user.model.PostSignUpReq;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                .userPw(postSignUpReq.getUserPw())
                .userName(postSignUpReq.getUserName())
                .userBirth(postSignUpReq.getUserBirth())
                .userGender(postSignUpReq.getUserGender())
                .userPhoneNumber(postSignUpReq.getUserPhoneNumber())
                .userEmail(postSignUpReq.getUserEmail())
                .userAddr(postSignUpReq.getUserAddr())
                .preferStyle(postSignUpReq.getPreferStyle())
                .upperType(postSignUpReq.getUpperType())
                .lowerType(postSignUpReq.getLowerType())
                .userMileage(postSignUpReq.getUserMileage())
                .createdAt(postSignUpReq.getCreatedAt())
                .updatedAt(postSignUpReq.getUpdatedAt())
                .status(postSignUpReq.getStatus())
                .build());
    }

}
