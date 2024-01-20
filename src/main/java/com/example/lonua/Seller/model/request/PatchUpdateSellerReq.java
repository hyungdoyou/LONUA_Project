package com.example.lonua.Seller.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUpdateSellerReq {
    private Integer sellerIdx;
    private String sellerPassword;
}
