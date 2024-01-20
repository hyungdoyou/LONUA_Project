package com.example.lonua.Seller.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginSellerReq {
    private String sellerEmail;
    private String sellerPassword;
}
