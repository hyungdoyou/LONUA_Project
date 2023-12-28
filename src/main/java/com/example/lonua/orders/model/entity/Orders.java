package com.example.lonua.orders.model.entity;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false)
    private Integer status;

}
