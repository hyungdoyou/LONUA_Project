package com.example.lonua.product.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReadOrdersProductRes {

    private Integer productIdx;
    private String brandName;
    private String productName;
    private Integer price;

}
