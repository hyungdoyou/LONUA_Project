package com.example.lonua.orders.model.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCreateOrdersRes {

    private String brandName;
    private String productName;
    private Integer salePrice;
}
