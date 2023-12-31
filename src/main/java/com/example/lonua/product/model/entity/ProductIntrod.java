package com.example.lonua.product.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductIntrod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productIntrodIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @Column(nullable = false, length = 200, unique = true)
    private String productIntrodImage;



}
