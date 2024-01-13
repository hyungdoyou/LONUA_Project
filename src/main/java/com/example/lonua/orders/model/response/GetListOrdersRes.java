package com.example.lonua.orders.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListOrdersRes {

    private String brandName;
    private String productName;
    private String productImage;
    private Integer price;
    private String createdAt;


}
