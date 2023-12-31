package com.example.lonua.user.model.entity.response;

import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserOrdersRes {

    private Integer ordersIdx;
    private GetReadOrdersProductRes getReadOrdersProductRes;
}
