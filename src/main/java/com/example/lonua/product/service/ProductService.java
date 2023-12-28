package com.example.lonua.product.service;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.CategoryException;
import com.example.lonua.product.model.PostRegReq;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.repository.ProductRepository;
import com.example.lonua.style.model.entity.Style;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void register(PostRegReq postRegReq) {
        Optional<Product> result = productRepository.findByProductName(postRegReq.getProductName());
        if(result.isPresent()) {
            throw new CategoryException(ErrorCode.DUPLICATED_PRODUCT, String.format("Product is %s", postRegReq.getProductName()));
        }

        productRepository.save(Product.builder()
                        .brand(Brand.builder()
                                .brandIdx(postRegReq.getBrand_idx())
                                .build())
                        .category(Category.builder()
                                .categoryIdx(postRegReq.getCategory_idx())
                                .build())
                        .style(Style.builder()
                                .styleIdx(postRegReq.getStyle_idx())
                                .build())
                        .productName(postRegReq.getProductName())
                        .productIntroduction(postRegReq.getProductIntroduction())
                        .quantity(postRegReq.getQuantity())
                        .price(postRegReq.getPrice())
                        .shoulderWidth(postRegReq.getShoulderWidth())
                        .chestSize(postRegReq.getChestSize())
                        .armLength(postRegReq.getArmLength())
                        .topLength(postRegReq.getTopLength())
                        .waistline(postRegReq.getWaistline())
                        .hipCircumference(postRegReq.getHipCircumference())
                        .thighCircumference(postRegReq.getThighCircumference())
                        .crotchLength(postRegReq.getCrotchLength())
                        .hemLength(postRegReq.getHemLength())
                        .totalBottomLength(postRegReq.getTotalBottomLength())
                        .upperType1Amount(0)
                        .upperType2Amount(0)
                        .upperType3Amount(0)
                        .lowerType1Amount(0)
                        .lowerType2Amount(0)
                        .lowerType3Amount(0)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .status(1)
                .build());
    }
}
