package com.example.lonua.brand.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUpdateBrandRes {

    private String brandName;

    private String brandImage;

    private String brandIntroduction;

    private String brandStyle;

    private String businessAddress;

    private String phoneNumber;

    private String businessRegistration;

    private String bankAccountNumber;

    private String returnAddress;

    private Integer returnCost;

    private String returnCourier;
}
