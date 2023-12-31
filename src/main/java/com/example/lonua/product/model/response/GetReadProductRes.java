package com.example.lonua.product.model.response;

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
    private String brandName;
    private String productName;
    private String productImage;
    private List<GetProductIntrodRes> getProductIntrodResList = new ArrayList<>();
    private Integer price;

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
}
