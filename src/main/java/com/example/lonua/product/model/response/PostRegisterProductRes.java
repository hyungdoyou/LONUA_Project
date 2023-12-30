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

    private Integer shoulderWidth;
    private Integer chestSize;
    private Integer armLength;
    private Integer topLength;

    private Integer waistline;
    private Integer hipCircumference;
    private Integer thighCircumference;
    private Integer crotchLength;
    private Integer hemLength;
    private Integer totalBottomLength;
}
