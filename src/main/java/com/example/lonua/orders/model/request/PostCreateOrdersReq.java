package com.example.lonua.orders.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateOrdersReq {

    private Integer amount;
    private String impUid;
    private String merchantUid;
    private String productName;
    private String payMethod;
    private List<Integer> productIdxList;

}
