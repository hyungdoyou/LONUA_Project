package com.example.lonua.product.model.response;

import com.example.lonua.brand.model.entity.Brand;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReadProductRes {
    private Integer productIdx;
    private String productName;
    private List<String> productImages;
    private List<String> productIntrodImages;

    private Integer price;
    private Integer salePrice;

    private Float shoulderWidth;
    private Float chestSize;
    private Float armLength;
    private Float topLength;

    private Float waistline;
    private Float hipCircumference;
    private Float thighCircumference;
    private Float crotchLength;
    private Float hemLength;
    private Float totalBottomLength;

    private Integer likeCount;

    private String brandName;
    private String brandImage;
    private String businessAddress;
    private String brandPhoneNumber;
    private String businessRegistration;
    private String returnAddress;
    private Integer returnCost;
    private String returnCourier;
}
