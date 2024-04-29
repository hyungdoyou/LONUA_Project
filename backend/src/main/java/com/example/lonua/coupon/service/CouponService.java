package com.example.lonua.coupon.service;


import com.example.lonua.common.BaseRes;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.coupon.model.request.DeleteCouponRemoveReq;
import com.example.lonua.coupon.model.request.PostCouponRegisterReq;
import com.example.lonua.coupon.model.response.GetCouponListRes;
import com.example.lonua.coupon.model.response.PostCouponRegisterRes;
import com.example.lonua.coupon.repository.CouponRepository;
import com.example.lonua.review.exception.ReviewNotFoundException;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional(readOnly = false)
    public BaseRes create(PostCouponRegisterReq request) {
        Coupon coupon = couponRepository.save(Coupon.builder()
                .couponName(request.getCouponName())
                .couponDiscountRate(request.getCouponDiscountRate())
                .status(true)
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜는 2개월 뒤로 임의 설정
                .couponExpirationDate(LocalDateTime.now().plusMonths(2L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(User.builder()
                        .userIdx(request.getUserIdx())
                        .build())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostCouponRegisterRes.builder()
                        .userIdx(coupon.getUser().getUserIdx())
                        .couponIdx(coupon.getCouponIdx())
                        .couponName(coupon.getCouponName())
                        .couponDiscountRate(coupon.getCouponDiscountRate())
                        .receivedDate(coupon.getReceivedDate())
                        .couponExpirationDate(coupon.getCouponExpirationDate())
                        .status(coupon.getStatus())
                        .build())
                .build();

    }

//    public BaseRes read(GetReadReq request) {
//        Optional<Coupon> byId = couponRepository.findById(request.getCouponIdx());
//        if (byId.isPresent()) {
//            Coupon coupon = byId.get();
//            return BaseRes.builder()
//                    .code(200)
//                    .isSuccess(true)
//                    .message("요청성공")
//                    .result(GetReadRes.builder()
//                            .couponIdx(coupon.getCouponIdx())
//                            .couponName(coupon.getCouponName())
//                            .couponDiscountRate(coupon.getCouponDiscountRate())
//                            .receivedDate(coupon.getReceivedDate())
//                            .couponExpirationDate(coupon.getCouponExpirationDate())
//                            .status(coupon.getStatus())
//                            .userIdx(coupon.getUser().getUserIdx())
//                            .build())
//                    .build();
//        }
//        return null;
//
//    }

    @Transactional(readOnly = true)
    public BaseRes list(User user) {
        List<Coupon> all = couponRepository.findAllByUserUserIdx(user.getUserIdx());
        List<GetCouponListRes> getListResCouponList = new ArrayList<>();

        for (Coupon coupon : all) {

            // 쿠폰 남은시간 일수로 계산
            String expiredDay = coupon.getCouponExpirationDate();
            LocalDateTime expirationTime = LocalDateTime.parse(expiredDay, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            LocalDateTime now = LocalDateTime.now();
            long remainDay = ChronoUnit.DAYS.between(now, expirationTime);

            GetCouponListRes getCouponListRes = GetCouponListRes.builder()
                    .couponIdx(coupon.getCouponIdx())
                    .couponName(coupon.getCouponName())
                    .couponDiscountRate(coupon.getCouponDiscountRate())
                    .receivedDate(coupon.getReceivedDate())
                    .couponExpirationDate(remainDay)
                    .status(coupon.getStatus())
                    .userIdx(coupon.getUser().getUserIdx())
                    .build();
            getListResCouponList.add(getCouponListRes);
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResCouponList)
                .build();
    }

    public BaseRes apply(Integer couponIdx) {
        Optional<Coupon> result = couponRepository.findByCouponIdx(couponIdx);

        if (result.isPresent()) {
            Coupon coupon = result.get();
            if (coupon.getStatus()) {
                coupon.setStatus(false);
                couponRepository.save(coupon);

                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("쿠폰 적용 성공")
                        .result(couponIdx)
                        .build();
            } else {
                return BaseRes.builder()
                        .code(400)
                        .isSuccess(false)
                        .message("쿠폰 적용 실패")
                        .result("요청 살패")
                        .build();
            }

        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("쿠폰 적용 실패")
                    .result("요청 살패")
                    .build();
        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(DeleteCouponRemoveReq request) {


        Integer result = couponRepository.deleteByCouponIdx(request.getCouponIdx());

        if (!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("쿠폰 삭제 성공")
                    .result("요청 성공")
                    .build();
        }
        return BaseRes.builder()
                .code(400)
                .isSuccess(false)
                .message("쿠폰 삭제 실패")
                .result("요청 실패")
                .build();
    }
}
