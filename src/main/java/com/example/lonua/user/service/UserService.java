package com.example.lonua.user.service;

import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.UserException;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.grade.repository.GradeRepository;
import com.example.lonua.user.model.PostSignUpReq;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .createdAt(postSignUpReq.getCreatedAt())
                .updatedAt(postSignUpReq.getUpdatedAt())
                .status(postSignUpReq.getStatus())
                .build());
    }

    // 사용자가 입력한 username을 DB에서 찾는 역할 수행
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByUserId(username);
        User user = null;
        if(result.isPresent()) {
            user = result.get();
        }
        return user; // 입력한 username이 있으면 Member 객체를 UserDetails 로 반환
    }

}
