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

    @OneToMany(mappedBy = "product")
    private List<ProductIntrod> productIntrodList = new ArrayList<>();

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

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    private Float shoulderWidth; // 어깨 너비
    private Float chestSize;  // 가슴 둘레
    private Float armLength;  // 팔 길이
    private Float topLength;  // 상의 총 길이

    private Float waistline;  // 허리 둘레
    private Float hipCircumference;  // 엉덩이 둘레
    private Float thighCircumference;  // 허벅지 둘레
    private Float crotchLength;  // 밑위 길이
    private Float hemLength;  // 밑단 길이
    private Float totalBottomLength;  // 하의 총 길이

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
