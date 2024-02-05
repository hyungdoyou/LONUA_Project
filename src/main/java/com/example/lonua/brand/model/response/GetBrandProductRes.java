package com.example.lonua.brand.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBrandProductRes {
    private Integer productIdx;
    private Integer brandIdx;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer salePrice;
    private Integer likeCount;
}
