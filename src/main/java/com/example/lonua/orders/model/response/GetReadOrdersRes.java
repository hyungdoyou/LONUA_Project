package com.example.lonua.orders.model.response;

import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import com.example.lonua.user.model.entity.response.PostUserOrdersRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReadOrdersRes {

    private String brandName;
    private String productName;
    private String productImage;

    private Integer price;
    private Integer salePrice;

    private String userName;
    private String userPhoneNumber;
    private String userAddr;
    private String deliveryMassage;

}
