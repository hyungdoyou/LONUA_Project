package com.example.lonua.Seller.model.request;

import com.example.lonua.user.model.entity.request.PatchUserUpdateReq;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupSellerReq {
    private String sellerEmail;
    private String sellerPassword;
    private Integer brandIdx;
    private String brandUniqueKey;
}
