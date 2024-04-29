package com.example.lonua.brand.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListAllBrandRes {
    private Integer brandIdx;
    private String brandName;
    private String brandIntroduction;
    private String brandImage;
    private String brandStyle;

    private List<GetBrandProductRes> getBrandProductResList;
}
