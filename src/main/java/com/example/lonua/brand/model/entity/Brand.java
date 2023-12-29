package com.example.lonua.brand.model.entity;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.product.model.entity.Product;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandIdx;

    @OneToMany(mappedBy = "brand")
    private List<Branch> branchList = new ArrayList<>();

    @OneToMany(mappedBy = "brand")
    private List<Product> productList = new ArrayList<>();

    @Column(nullable = false, unique = true, length = 45)
    private String brandName;

    @Column(nullable = false, length = 500)
    private String brandIntroduction;

    @Column(nullable = false, length = 200, unique = true)
    private String brandImage;

    @Column(nullable = false, length = 10)
    private String brandStyle;

    @Column(nullable = false, unique = true, length = 200)
    private String businessAddress;

    @Column(nullable = false, unique = true, length = 20)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 45)
    private String businessRegistration;

    @Column(nullable = false, unique = true, length = 45)
    private String bankAccountNumber;

    @Column(nullable = false, unique = true, length = 200)
    private String returnAddress;

    @Column(nullable = false)
    private Integer returnCost;

    @Column(nullable = false, length = 45)
    private String returnCourier;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer status;
}
