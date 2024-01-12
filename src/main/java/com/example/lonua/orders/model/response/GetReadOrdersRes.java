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
    private Integer ordersIdx;

    private PostUserOrdersRes postUserOrdersRes;
    private GetReadOrdersProductRes getReadOrdersProductRes;

}
