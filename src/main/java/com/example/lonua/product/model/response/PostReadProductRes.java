package com.example.lonua.product.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReadProductRes {

    private Integer productIdx;
    private String productName;
    private Integer price;

}
