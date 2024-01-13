package com.example.lonua.likes.model.response;

import com.example.lonua.brand.model.entity.Brand;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListLikesRes {

    private String brandName;
    private String productName;
    private Integer price;
    private Integer likeCount;

}
