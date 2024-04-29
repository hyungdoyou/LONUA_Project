package com.example.lonua.brand.model.entity;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.brand.model.request.PatchUpdateBrandReq;
import com.example.lonua.product.model.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "brand")
    protected List<Seller> sellerList = new ArrayList<>();

    @Column(nullable = false, unique = true, length = 45)
    private String brandUniqueKey;

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

    @Column(nullable = false, unique = true, length = 10)
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
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    public void update(PatchUpdateBrandReq patchUpdateBrandReq, String brandFileName) {
        if (patchUpdateBrandReq.getBrandName() != null) {
            this.brandName = patchUpdateBrandReq.getBrandName();
        }
        if (patchUpdateBrandReq.getBrandIntroduction() != null) {
            this.brandIntroduction = patchUpdateBrandReq.getBrandIntroduction();
        }
        if (patchUpdateBrandReq.getBrandStyle() != null) {
            this.brandStyle = patchUpdateBrandReq.getBrandStyle();
        }
        if (patchUpdateBrandReq.getBusinessAddress() != null) {
            this.businessAddress = patchUpdateBrandReq.getBusinessAddress();
        }
        if (patchUpdateBrandReq.getPhoneNumber() != null) {
            this.phoneNumber = patchUpdateBrandReq.getPhoneNumber();
        }
        if (patchUpdateBrandReq.getBusinessRegistration() != null) {
            this.businessRegistration = patchUpdateBrandReq.getBusinessRegistration();
        }
        if (patchUpdateBrandReq.getBankAccountNumber() != null) {
            this.bankAccountNumber = patchUpdateBrandReq.getBankAccountNumber();
        }
        if (patchUpdateBrandReq.getReturnAddress() != null) {
            this.returnAddress = patchUpdateBrandReq.getReturnAddress();
        }
        if (patchUpdateBrandReq.getReturnCost() != null) {
            this.returnCost = patchUpdateBrandReq.getReturnCost();
        }
        if (patchUpdateBrandReq.getReturnCourier() != null) {
            this.returnCourier = patchUpdateBrandReq.getReturnCourier();
        }
        if (brandFileName != null) {
            this.brandImage = brandFileName;
        }
    }
}
