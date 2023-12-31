package com.example.lonua.orders.model.response;


import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListOrdersRes {

    private Integer ordersIdx;
    private GetReadOrdersProductRes getReadOrdersProductRes;
}
