package com.example.lonua.product.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateProductRes {
    private String productName;
    private Integer quantity;
    private Integer price;
    private Integer salePrice;
}
