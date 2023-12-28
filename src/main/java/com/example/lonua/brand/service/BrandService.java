package com.example.lonua.brand.service;

import com.example.lonua.brand.model.PostBrandReq;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.brand.repository.BrandRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void register(PostBrandReq postBrandReq) {
        if(!brandRepository.findByBrandName(postBrandReq.getBrandName()).isPresent()) {
            brandRepository.save(Brand.builder()
                    .brandName(postBrandReq.getBrandName())
                    .brandIntroduction(postBrandReq.getBrandIntroduction())
                    .brandImgUrl(postBrandReq.getBrandImgUrl())
                    .brandStyle(postBrandReq.getBrandStyle())
                    .businessAddress(postBrandReq.getBusinessAddress())
                    .phoneNumber(postBrandReq.getPhoneNumber())
                    .businessRegistration(postBrandReq.getBusinessRegistration())
                    .bankAccountNumber(postBrandReq.getBankAccountNumber())
                    .returnAddress(postBrandReq.getReturnAddress())
                    .returnCost(postBrandReq.getReturnCost())
                    .returnCourier(postBrandReq.getReturnCourier())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .status(1)
                    .build());
        }
    }
}
