package com.example.lonua.style.model.entity;

import com.example.lonua.product.model.entity.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer styleIdx;

    @OneToMany(mappedBy = "style")
    private List<Product> productList = new ArrayList<>();

    @Column(nullable = false)
    private Integer styleType;

}
