package com.example.lonua.product.model.entity;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.like.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.style.model.entity.Style;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productIdx;

    @OneToMany(mappedBy = "product")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Orders> ordersList = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_idx")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_idx")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Style_idx")
    private Style style;

    @Column(nullable = false, length = 45)
    private String productName;

    @Column(nullable = false, length = 200, unique = true)
    private String productImage;

    @Column(nullable = false, length = 200, unique = true)
    private String productIntroductionImage;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    private Integer shoulderWidth;
    private Integer chestSize;
    private Integer armLength;
    private Integer topLength;

    private Integer waistline;
    private Integer hipCircumference;
    private Integer thighCircumference;
    private Integer crotchLength;
    private Integer hemLength;
    private Integer totalBottomLength;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer status;

    private Integer upperType1Amount;
    private Integer upperType2Amount;
    private Integer upperType3Amount;

    private Integer lowerType1Amount;
    private Integer lowerType2Amount;
    private Integer lowerType3Amount;
}
