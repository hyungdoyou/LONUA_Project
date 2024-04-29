package com.example.lonua.product.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductIntrodImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productIntrodImageIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @Column(nullable = false, length = 500)
    private String productIntrodImage;

}
