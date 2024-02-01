package com.example.lonua.brand.model.response;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListBrandRes {
    private Integer brandIdx;
    private String brandName;
    private String brandIntroduction;
    private String brandImage;
    private String brandStyle;

}
