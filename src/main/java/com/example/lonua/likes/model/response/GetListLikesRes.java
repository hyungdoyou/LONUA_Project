package com.example.lonua.likes.model.response;

import com.example.lonua.brand.model.entity.Brand;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListLikesRes {

    private Integer likesIdx;
    private String brandName;
    private Integer productIdx;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer salePrice;
}
