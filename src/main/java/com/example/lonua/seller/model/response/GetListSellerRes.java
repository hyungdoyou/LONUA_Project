package com.example.lonua.seller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListSellerRes {
    private Integer sellerIdx;
    private String brandName;
    private String sellerEmail;
    private String sellerName;
}
