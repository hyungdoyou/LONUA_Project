package com.example.lonua.product.model.response;


import com.example.lonua.product.model.entity.ProductImage;
import com.example.lonua.product.model.entity.ProductIntrodImage;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegisterProductRes {

    private Integer productIdx;

    private String productName;
    private Integer quantity;
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

    private List<String> productImage;
    private List<String> productIntroductionImage;
}
