package com.example.lonua.coupon.service;


import com.example.lonua.common.BaseRes;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.coupon.model.request.DeleteCouponRemoveReq;
import com.example.lonua.coupon.model.request.PostCouponRegisterReq;
import com.example.lonua.coupon.model.response.GetCouponListRes;
import com.example.lonua.coupon.model.response.PostCouponRegisterRes;
import com.example.lonua.coupon.repository.CouponRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional(readOnly = false)
    public BaseRes create(User user, PostCouponRegisterReq request) {
        Coupon coupon = couponRepository.save(Coupon.builder()
                .couponName(request.getCouponName())
                .couponDiscountRate(request.getCouponDiscountRate())
                .status(request.getStatus())
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜는 1년 후로 임의로 설정
                // TODO 만료일을 어떻게 집어 넣을지 고민해봐야한다 일단 1년으로 간다
                .couponExpirationDate(LocalDateTime.now().plusYears(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(User.builder()
                        .userIdx(user.getUserIdx())
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
            GetCouponListRes getCouponListRes = GetCouponListRes.builder()
                    .couponIdx(coupon.getCouponIdx())
                    .couponName(coupon.getCouponName())
                    .couponDiscountRate(coupon.getCouponDiscountRate())
                    .receivedDate(coupon.getReceivedDate())
                    .couponExpirationDate(coupon.getCouponExpirationDate())
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

    @Transactional(readOnly = false)
    public BaseRes delete(DeleteCouponRemoveReq request) {
        Coupon coupon = Coupon.builder()
                .couponIdx(request.getCouponIdx())
                .build();

        couponRepository.delete(coupon);

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result("쿠폰 삭제에 성공했습니다.")
                .build();
    }
}
