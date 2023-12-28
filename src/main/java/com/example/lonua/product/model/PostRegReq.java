package com.example.lonua.product.model;


import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.style.model.entity.Style;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegReq {

    private Integer brand_idx;
    private Integer category_idx;
    private Integer style_idx;

    private String productName;

    private String productIntroduction;

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

    private Integer upperType1Amount;
    private Integer upperType2Amount;
    private Integer upperType3Amount;

    private Integer lowerType1Amount;
    private Integer lowerType2Amount;
    private Integer lowerType3Amount;

}
