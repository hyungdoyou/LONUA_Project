package com.example.lonua.user.service;

import com.example.lonua.common.BaseRes;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.coupon.repository.CouponRepository;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.user.config.utils.JwtUtils;
import com.example.lonua.user.exception.UserAccountException;
import com.example.lonua.user.exception.UserDuplicateException;
import com.example.lonua.user.exception.UserNotFoundException;
import com.example.lonua.user.model.entity.request.PostCheckPasswordReq;
import com.example.lonua.user.model.entity.request.PostUserLoginReq;
import com.example.lonua.user.model.entity.request.PostSignUpReq;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.model.entity.request.PatchUserUpdateReq;
import com.example.lonua.user.model.entity.response.*;
import com.example.lonua.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailSender;
    private final EmailVerifyService emailVerifyService;
    private final CouponRepository couponRepository;


    // 회원가입
    @Transactional(readOnly = false)
    public BaseRes signup(PostSignUpReq postSignUpReq) {
        Optional<User> resultEmail = userRepository.findByUserEmail(postSignUpReq.getUserEmail());
        Optional<User> resultPhoneNumber = userRepository.findByUserPhoneNumber(postSignUpReq.getUserPhoneNumber());

        // 중복된 이메일에 대한 예외처리
        if (resultEmail.isPresent()) {
            throw UserDuplicateException.forSignupEmail(postSignUpReq.getUserEmail());
        }

        // 중복된 휴대전화번호에 대한 예외처리
        if(resultPhoneNumber.isPresent()) {
            throw UserDuplicateException.forPhoneNumber(postSignUpReq.getUserPhoneNumber());
        }

        User user = User.builder()
                .grade(Grade.builder()
                        .gradeIdx(1) // 처음 회원 가입 시 회원등급은 반드시 1번 등급(실버)으로 가입
                        .build())
                .userPassword(passwordEncoder.encode(postSignUpReq.getUserPassword()))
                .name(postSignUpReq.getUserName())
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
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build();

        userRepository.save(user);

        couponRepository.save(Coupon.builder()
                .couponName("회원가입 감사 10% 쿠폰")
                .couponDiscountRate(10)
                .status(true)
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜 : 1달 뒤
                .couponExpirationDate(LocalDateTime.now().plusMonths(2L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(user)
                .build());

        couponRepository.save(Coupon.builder()
                .couponName(LocalDateTime.now().getMonthValue() + " 월 LONUA 정기 15% 쿠폰")
                .couponDiscountRate(15)
                .status(true)
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜 : 1달 뒤
                .couponExpirationDate(LocalDateTime.now().plusMonths(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(user)
                .build());

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("회원가입에 성공하였습니다.")
                .result(PostSignupRes.builder()
                        .userEmail(user.getUserEmail())
                        .userName(user.getName())
                        .build())
                .build();

        return baseRes;
    }

    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<User> userList = userRepository.findUserList(pageable);

        List<GetListUserRes> getListUserResList = new ArrayList<>();
        for (User user : userList) {

            GetListUserRes getListUserRes = GetListUserRes.builder()
                    .userIdx(user.getUserIdx())
                    .userEmail(user.getUserEmail())
                    .name(user.getName())
                    .userBirth(user.getUserBirth())
                    .userGender(user.getUserGender())
                    .userAddr(user.getUserAddr())
                    .userPhoneNumber(user.getUserPhoneNumber())
                    .preferStyle(user.getPreferStyle())
                    .upperType(user.getUpperType())
                    .lowerType(user.getLowerType())
                    .gradeType(user.getGrade().getGradeType())
                    .build();

            getListUserResList.add(getListUserRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListUserResList)
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes read(String email) {
        Optional<User> result = userRepository.findUser(email);

        if(result.isPresent()) {
            User user = result.get();

            GetListUserRes getListUserRes = GetListUserRes.builder()
                    .userIdx(user.getUserIdx())
                    .userEmail(user.getUserEmail())
                    .name(user.getName())
                    .userBirth(user.getUserBirth())
                    .userGender(user.getUserGender())
                    .userPhoneNumber(user.getUserPhoneNumber())
                    .userAddr(user.getUserAddr())
                    .preferStyle(user.getPreferStyle())
                    .mileage(user.getUserMileage())
                    .upperType(user.getUpperType())
                    .lowerType(user.getLowerType())
                    .gradeType(user.getGrade().getGradeType())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListUserRes)
                    .build();
        } else {
            throw UserNotFoundException.forEmail(email);
        }
    }

    // 회원 로그인
    @Transactional(readOnly = false)
    public BaseRes login(PostUserLoginReq postUserLoginReq) {
        Optional<User> result = userRepository.findByUserEmail(postUserLoginReq.getEmail());

        if(result.isEmpty()) {
            throw UserNotFoundException.forEmail(postUserLoginReq.getEmail());
        }

        User user = result.get();
        if (passwordEncoder.matches(postUserLoginReq.getPassword(), user.getPassword()) && user.getStatus().equals(true)) {
            PostUserLoginRes postUserLoginRes = PostUserLoginRes.builder()
                    .token(JwtUtils.generateAccessToken(user, secretKey, expiredTimeMs))
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("로그인에 성공하였습니다.")
                    .result(postUserLoginRes)
                    .build();
        } else {
            throw UserAccountException.forInvalidPassword(postUserLoginReq.getPassword());
        }
    }

    // 회원정보 수정을 위한 비밀번호 체크
    @Transactional
    public Boolean checkPassword(User user, PostCheckPasswordReq postCheckPasswordReq) {
        if (passwordEncoder.matches(postCheckPasswordReq.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }



    // 인증메일 발송
    @Transactional(readOnly = false)
    public void sendEmail(PostSignUpReq postSignUpReq) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(postSignUpReq.getUserEmail());
        message.setSubject("[LONUA] 회원가입을 완료하기 위해서 이메일 인증을 진행해 주세요"); // 메일 제목

        String uuid = UUID.randomUUID().toString();
        message.setText("https://www.lonuashop.kro.kr/api/user/verify?email=" + postSignUpReq.getUserEmail() + "&uuid=" + uuid);    // 메일 내용

        emailSender.send(message);
        emailVerifyService.create(postSignUpReq.getUserEmail(), uuid);
    }

    // 메일 인증 완료 후 회원 상태 수정
    @Transactional(readOnly = false)
    public BaseRes updateStatus(String email) {
        Optional<User> result = userRepository.findByUserEmail(email);
        if (result.isPresent()) {
            User user = result.get();
            user.setStatus(true);
            userRepository.save(user);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("메일 인증에 성공하였습니다.")
                    .result(GetEmailVerifyRes.builder()
                            .email(user.getUserEmail())
                            .status(user.getStatus())
                            .build())
                    .build();
        } else {
            throw UserNotFoundException.forEmail(email);
        }
    }

    // 카카오 회원가입
    @Transactional
    public void kakaoSignup(String nickName) {

        User user = User.builder()
                .grade(Grade.builder()
                        .gradeIdx(1) // 처음 회원 가입 시 회원등급은 반드시 1번 등급(실버)으로 가입
                        .build())
                .userEmail(nickName)
                .userPassword(passwordEncoder.encode("kako"))
                .name(nickName)
                .userBirth("0000-00-00")
                .userGender("K")
                .userPhoneNumber("kakao")
                .userAddr("카카오 로그인")
                .preferStyle("캐쥬얼")
                .upperType(1)
                .lowerType(1)
                .userMileage(0)
                .authority("ROLE_USER")
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .status(false)
                .build();

        user = userRepository.save(user);

        couponRepository.save(Coupon.builder()
                .couponName("회원가입 감사 10% 쿠폰")
                .couponDiscountRate(10)
                .status(true)
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜 : 1달 뒤
                .couponExpirationDate(LocalDateTime.now().plusMonths(2L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(user)
                .build());

        couponRepository.save(Coupon.builder()
                .couponName(LocalDateTime.now().getMonthValue() + " 월 LONUA 정기 15% 쿠폰")
                .couponDiscountRate(15)
                .status(true)
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜 : 1달 뒤
                .couponExpirationDate(LocalDateTime.now().plusMonths(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(user)
                .build());
    }

    // 회원 이메일 검증
    public User getUserEmail(String email) {
        Optional<User> result = userRepository.findByUserEmail(email);

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;  // 카카오 로그인 시 null이 반환되면 가입 진행토록 설정
        }
    }

    // 회원정보 수정
    @Transactional(readOnly = false)
    public BaseRes update(String userEmail, PatchUserUpdateReq patchUserUpdateReq) {
        Optional<User> result = userRepository.findByUserEmail(userEmail);

        if (result.isPresent()) {
            User user = result.get();

            if(patchUserUpdateReq.getUserPassword() != null) {
                user.update(patchUserUpdateReq, passwordEncoder.encode(patchUserUpdateReq.getUserPassword()));
            } else {
                user.update(patchUserUpdateReq, null);
            }
            user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            userRepository.save(user);

            PatchUserUpdateRes patchUserUpdateRes = PatchUserUpdateRes.builder()
                    .userAddr(user.getUserAddr())
                    .userPhoneNumber(user.getUserPhoneNumber())
                    .preferStyle(user.getPreferStyle())
                    .upperType(user.getUpperType())
                    .lowerType(user.getLowerType())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("회원정보 수정 성공")
                    .result(patchUserUpdateRes)
                    .build();
        } else {
            throw UserNotFoundException.forEmail(userEmail);        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer idx) {

        Integer result = userRepository.deleteByUserIdx(idx);

        if (!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("회원이 삭제되었습니다.")
                    .build();
        } else {
            throw UserNotFoundException.forIdx(idx);
        }
    }

    @Transactional(readOnly = false)
    public BaseRes cancel(Integer userIdx) {
        Optional<User> byUserIdx = userRepository.findByUserIdx(userIdx);

        if (byUserIdx.isPresent()) {
            User loginUser = byUserIdx.get();
            loginUser.setStatus(false);
            userRepository.save(loginUser);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("회원의 상태가 탈퇴 상태로 변경되었습니다.")
                    .build();
        } else {
            throw UserNotFoundException.forIdx(userIdx);
        }
    }
}
