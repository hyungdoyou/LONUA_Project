package com.example.lonua.seller.service;

import com.example.lonua.seller.exception.SellerAccountException;
import com.example.lonua.seller.exception.SellerEmailDuplicateException;
import com.example.lonua.seller.exception.SellerNotFoundException;
import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.seller.model.request.PatchUpdateSellerReq;
import com.example.lonua.seller.model.request.PostLoginSellerReq;
import com.example.lonua.seller.model.request.PostSignupSellerReq;
import com.example.lonua.seller.model.response.GetListSellerRes;
import com.example.lonua.seller.model.response.PostLoginSellerRes;
import com.example.lonua.seller.model.response.PostSignupSellerRes;
import com.example.lonua.seller.repository.SellerRepository;
import com.example.lonua.brand.exception.BrandNotFoundException;
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
            throw new SellerEmailDuplicateException(postSignupSellerReq.getSellerEmail());
        }

        Optional<Brand> brandResult = brandRepository.findByBrandIdx(postSignupSellerReq.getBrandIdx());
        if(brandResult.isPresent()) {
            Brand brand = brandResult.get();
            if(brand.getBrandUniqueKey().equals(postSignupSellerReq.getBrandUniqueKey())) {
                sellerRepository.save(Seller.builder()
                                .sellerEmail(postSignupSellerReq.getSellerEmail())
                                .sellerPassword(passwordEncoder.encode(postSignupSellerReq.getSellerPassword()))
                                .sellerName(postSignupSellerReq.getSellerName())
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
                throw SellerAccountException.forInvalidUniqueKey(postSignupSellerReq.getBrandUniqueKey());
            }
        } else {
            throw BrandNotFoundException.forIdx(postSignupSellerReq.getBrandIdx());
        }
    }

    public BaseRes login(PostLoginSellerReq postLoginSellerReq) {
        Optional<Seller> result = sellerRepository.findBySellerEmail(postLoginSellerReq.getEmail());

        if(result.isEmpty()) {
            throw SellerNotFoundException.forEmail(postLoginSellerReq.getEmail());
        }

        Seller seller = result.get();
        if(passwordEncoder.matches(postLoginSellerReq.getPassword(), seller.getSellerPassword())) {
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
            throw SellerAccountException.forInvalidPassword(postLoginSellerReq.getPassword());
        }
    }
    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Seller> sellerList = sellerRepository.findSellerList(pageable);

        List<GetListSellerRes> getListSellerResList = new ArrayList<>();
        for(Seller seller : sellerList) {
            GetListSellerRes getListSellerRes = GetListSellerRes.builder()
                                                    .sellerIdx(seller.getSellerIdx())
                                                    .sellerEmail(seller.getSellerEmail())
                                                    .sellerName(seller.getSellerName())
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
            seller.update(passwordEncoder.encode(patchUpdateSellerReq.getSellerPassword()));
            seller.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result("판매자 계정 정보 수정을 성공하였습니다.")
                    .build();
        } else {
            throw SellerNotFoundException.forIdx(patchUpdateSellerReq.getSellerIdx());
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
            throw SellerNotFoundException.forIdx(idx);
        }
    }
}
