package com.example.lonua.orders.model.entity;

import com.example.lonua.product.model.entity.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersProductIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orders_idx")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;
}
