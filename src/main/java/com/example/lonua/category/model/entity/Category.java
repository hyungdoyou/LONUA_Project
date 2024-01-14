package com.example.lonua.category.model.entity;

import com.example.lonua.category.model.request.PostCategoryReq;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.request.PatchUpdateProductReq;
import io.swagger.models.auth.In;
import lombok.*;

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryIdx;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

    @Column(nullable = false, length = 45)
    private String categoryName;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    public void update(PostCategoryReq postCategoryReq) {
        if (postCategoryReq.getCategoryName() != null) {
            this.categoryName = postCategoryReq.getCategoryName();
        }
    }
}
