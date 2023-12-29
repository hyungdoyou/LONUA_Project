package com.example.lonua.product.model.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegisterProductReq {

    private Integer brand_idx;
    private Integer category_idx;
    private Integer style_idx;

    private String productName;

    private MultipartFile productImage;
    private MultipartFile productIntroductionImage;

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
