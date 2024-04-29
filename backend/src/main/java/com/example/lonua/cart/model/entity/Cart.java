package com.example.lonua.cart.model.entity;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;
}
