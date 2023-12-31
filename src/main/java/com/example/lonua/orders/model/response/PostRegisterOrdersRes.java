package com.example.lonua.orders.model.response;

import com.example.lonua.product.model.response.PostReadProductRes;
import com.example.lonua.user.model.entity.response.PostUserLoginRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterOrdersRes {
    private Integer ordersIdx;

    private PostUserLoginRes postUserLoginRes;
    private PostReadProductRes postReadProductRes;

}
