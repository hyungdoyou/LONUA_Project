package com.example.lonua.Seller.service;

import com.example.lonua.Seller.model.entity.Seller;
import com.example.lonua.Seller.model.request.PatchUpdateSellerReq;
import com.example.lonua.Seller.model.request.PostLoginSellerReq;
import com.example.lonua.Seller.model.request.PostSignupSellerReq;
import com.example.lonua.Seller.model.response.GetListSellerRes;
import com.example.lonua.Seller.model.response.PostLoginSellerRes;
import com.example.lonua.Seller.model.response.PostSignupSellerRes;
import com.example.lonua.Seller.repository.SellerRepository;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.brand.repository.BrandRepository;
import com.example.lonua.common.BaseRes;
import com.example.lonua.user.config.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    private final SellerRepository sellerRepository;
    private final BrandRepository brandRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public BaseRes signup(PostSignupSellerReq postSignupSellerReq) {
        Optional<Seller> sellerResult = sellerRepository.findBySellerEmail(postSignupSellerReq.getSellerEmail());

        if(sellerResult.isPresent()) {
            // TODO : 이메일 중복 예외처리 추가
        }

        Optional<Brand> brandResult = brandRepository.findByBrandIdx(postSignupSellerReq.getBrandIdx());
        if(brandResult.isPresent()) {
            Brand brand = brandResult.get();
            if(brand.getBrandUniqueKey().equals(postSignupSellerReq.getBrandUniqueKey())) {
                sellerRepository.save(Seller.builder()
                                .sellerEmail(postSignupSellerReq.getSellerEmail())
                                .sellerPassword(passwordEncoder.encode(postSignupSellerReq.getSellerPassword()))
                                .authority("ROLE_SELLER")
                                .brand(brand)
                                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                                .status(true)
                        .build());

                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("판매자 계정 생성 성공")
                        .result(PostSignupSellerRes.builder().sellerEmail(postSignupSellerReq.getSellerEmail()).build())
                        .build();
            } else {
                //TODO : 브랜드 고유번호가 일치하지 않을 시 예외처리 추가
                return null;
            }
        } else {
            //TODO : 브랜드IDX 로 브랜드를 찾을 수 없을 시 예외처리 추가
            return null;
        }
    }

    public BaseRes login(PostLoginSellerReq postLoginSellerReq) {
        Optional<Seller> result = sellerRepository.findBySellerEmail(postLoginSellerReq.getSellerEmail());

        if(result.isEmpty()) {
            // TODO : 예외처리 추가
            return null;
        }

        Seller seller = result.get();
        if(passwordEncoder.matches(postLoginSellerReq.getSellerPassword(), seller.getSellerPassword())) {
            PostLoginSellerRes postLoginSellerRes = PostLoginSellerRes.builder()
                    .token(JwtUtils.generateAccessTokenForSeller(seller, secretKey, expiredTimeMs))
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("로그인에 성공하였습니다.")
                    .result(postLoginSellerRes)
                    .build();
        } else {
            // TODO : 예외처리 추가
            return null;
        }
    }
    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Seller> sellerList = sellerRepository.findAll(pageable);

        List<GetListSellerRes> getListSellerResList = new ArrayList<>();
        for(Seller seller : sellerList) {
            GetListSellerRes getListSellerRes = GetListSellerRes.builder()
                                                    .sellerIdx(seller.getSellerIdx())
                                                    .sellerEmail(seller.getSellerEmail())
                                                    .brandName(seller.getBrand().getBrandName())
                                                    .build();

            getListSellerResList.add(getListSellerRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListSellerResList)
                .build();
    }

    @Transactional
    public BaseRes update(PatchUpdateSellerReq patchUpdateSellerReq) {
        Optional<Seller> result = sellerRepository.findBySellerIdx(patchUpdateSellerReq.getSellerIdx());

        if(result.isPresent()) {
            Seller seller = result.get();
            seller.update(patchUpdateSellerReq);
            seller.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("판매자 계정 정보 수정을 성공하였습니다.")
                    .build();
        } else {
            // TODO : 예외처리 추가
            return null;
        }
    }

    @Transactional
    public BaseRes delete(Integer idx) {
        Integer result = sellerRepository.deleteBySellerIdx(idx);

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("판매자 계정을 삭제하였습니다.")
                    .build();
        } else {
            // TODO : 예외처리 추가
            return null;
        }
    }
}
