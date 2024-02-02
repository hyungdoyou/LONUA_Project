package com.example.lonua.orders.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListOrdersRes {

    private Integer ordersProductIdx;
    private Integer productIdx;
    private String impUid;
    private String brandName;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer salePrice;
    private String createdAt;
    private String status;


}
