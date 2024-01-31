package com.example.lonua.product.model.response;

import com.example.lonua.brand.model.entity.Brand;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListProductRes {

    private Integer productIdx;
    private String brandName;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer salePrice;
    private Integer likeCount;
}
