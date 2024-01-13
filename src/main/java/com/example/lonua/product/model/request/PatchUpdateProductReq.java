package com.example.lonua.product.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateProductReq {

    private Integer productIdx;
    private String productName;
    private Integer quantity;
    private Integer price;

}
