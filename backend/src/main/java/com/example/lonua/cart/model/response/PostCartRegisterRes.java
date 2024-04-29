package com.example.lonua.cart.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostCartRegisterRes {

    private Integer cartIdx;
    private Integer productIdx;
    private Integer userIdx;
    private String createdAt;
    private String updatedAt;
    private Boolean status;
}
