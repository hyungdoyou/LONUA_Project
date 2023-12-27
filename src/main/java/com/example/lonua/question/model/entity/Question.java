package com.example.lonua.question.model.entity;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @Column(nullable = false, length = 45)
    private String questionType;

    @Column(nullable = false, length = 500)
    private String questionContent;

    @Column(nullable = false, length = 1)
    private String hasAnswer;

    @Column(nullable = false, length = 1)
    private String isSecret;

}
