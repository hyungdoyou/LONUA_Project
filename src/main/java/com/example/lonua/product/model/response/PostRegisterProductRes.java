package com.example.lonua.product.model.response;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegisterProductRes {

    private Integer productIdx;

    private String productName;

    private String productImage;
    private String productIntroductionImage;

    private Integer quantity;
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
