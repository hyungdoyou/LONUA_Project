package com.example.lonua.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetCartListRes {
    private Integer cartIdx;
    private String brandName;
    private Integer productIdx;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer salePrice;
}
