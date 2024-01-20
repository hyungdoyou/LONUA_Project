package com.example.lonua.category.model.entity;

import com.example.lonua.category.model.request.PatchUpdateCategoryReq;
import com.example.lonua.category.model.request.PostRegisterCategoryReq;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryIdx;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

    @Column(nullable = false, length = 45, unique = true)
    private String categoryName;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    public void update(PatchUpdateCategoryReq patchUpdateCategoryReq) {
        if (patchUpdateCategoryReq.getCategoryName() != null) {
            this.categoryName = patchUpdateCategoryReq.getCategoryName();
        }
    }
}
