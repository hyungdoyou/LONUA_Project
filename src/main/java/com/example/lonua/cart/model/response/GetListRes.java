package com.example.lonua.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetListRes {
    private Integer cartIdx;
    private String productName;
    private Integer price;
}
