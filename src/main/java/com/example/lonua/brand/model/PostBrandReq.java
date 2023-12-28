package com.example.lonua.brand.model;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.product.model.entity.Product;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostBrandReq {

//    private List<Branch> branchList = new ArrayList<>();
//    private List<Product> productList = new ArrayList<>();

    private String brandName;

    private String brandIntroduction;

    private String brandImgUrl;

    private String brandStyle;

    private String businessAddress;

    private String phoneNumber;

    private String businessRegistration;

    private String bankAccountNumber;

    private String returnAddress;

    private Integer returnCost;

    private String returnCourier;

}
