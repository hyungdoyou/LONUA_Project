package com.example.lonua.coupon.service;


import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.config.BaseRes;
import com.example.lonua.coupon.model.entity.Coupon;
import com.example.lonua.coupon.model.request.DeleteRemoveReq;
import com.example.lonua.coupon.model.request.GetReadReq;
import com.example.lonua.coupon.model.request.PostRegisterReq;
import com.example.lonua.coupon.model.response.GetListRes;
import com.example.lonua.coupon.model.response.GetReadRes;
import com.example.lonua.coupon.model.response.PostRegisterRes;
import com.example.lonua.coupon.repository.CouponRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public BaseRes create(PostRegisterReq request) {
        Coupon coupon = couponRepository.save(Coupon.builder()
                .couponName(request.getCouponName())
                .couponDiscountRate(request.getCouponDiscountRate())
                .status(request.getStatus())
                .receivedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                // 만료날짜는 1년 후로 임의로 설정
                // TODO 만료일을 어떻게 집어 넣을지 고민해봐야한다 일단 1년으로 간다
                .couponExpirationDate(LocalDateTime.now().plusYears(1L).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .user(User.builder()
                        .userIdx(request.getUserIdx())
                        .build())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostRegisterRes.builder()
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

    public BaseRes read(GetReadReq request) {
        Optional<Coupon> byId = couponRepository.findById(request.getCouponIdx());
        if (byId.isPresent()) {
            Coupon coupon = byId.get();
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .result(GetReadRes.builder()
                            .couponIdx(coupon.getCouponIdx())
                            .couponName(coupon.getCouponName())
                            .couponDiscountRate(coupon.getCouponDiscountRate())
                            .receivedDate(coupon.getReceivedDate())
                            .couponExpirationDate(coupon.getCouponExpirationDate())
                            .status(coupon.getStatus())
                            .userIdx(coupon.getUser().getUserIdx())
                            .build())
                    .build();
        }
        return null;

    }

    public BaseRes list() {
        List<Coupon> all = couponRepository.findAll();
        List<GetListRes> getListResList = new ArrayList<>();

        for (Coupon coupon : all) {
            GetListRes getListRes = GetListRes.builder()
                    .couponIdx(coupon.getCouponIdx())
                    .couponName(coupon.getCouponName())
                    .couponDiscountRate(coupon.getCouponDiscountRate())
                    .receivedDate(coupon.getReceivedDate())
                    .couponExpirationDate(coupon.getCouponExpirationDate())
                    .status(coupon.getStatus())
                    .userIdx(coupon.getUser().getUserIdx())
                    .build();
            getListResList.add(getListRes);
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResList)
                .build();
    }

    public BaseRes delete(DeleteRemoveReq request) {
        Coupon coupon = Coupon.builder()
                .couponIdx(request.getCouponIdx())
                .build();

        couponRepository.delete(coupon);

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .build();
    }
}
