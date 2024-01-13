package com.example.lonua.product.model.request;


import com.example.lonua.product.model.response.GetProductIntrodRes;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
