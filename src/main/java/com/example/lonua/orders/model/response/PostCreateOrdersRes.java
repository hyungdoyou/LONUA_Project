package com.example.lonua.orders.model.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateOrdersRes {

    private String userName;
    private String userAddr;
    private String userPhoneNumber;
    private Integer totalAmount;
    private String impUid;
    private String payMethod;
    private List<GetCreateOrdersRes> productList = new ArrayList<>();
}
