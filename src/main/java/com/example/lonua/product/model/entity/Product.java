package com.example.lonua.product.model.entity;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.likes.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.style.model.entity.Style;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductIntrodImage> productIntrodImageList = new ArrayList<>();

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

    private String createdAt;
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    @Version
    private Integer likeCount; // "격리성" 실습을 위한 좋아요 수

    private Integer upperType1Count;  // 상체 마름 회원이 구매한 수
    private Integer upperType2Count;  // 상체 보통 회원이 구매한 수
    private Integer upperType3Count;  // 상체 비만 회원이 구매한 수

    private Integer lowerType1Count;  // 하체 마름 회원이 구매한 수
    private Integer lowerType2Count;  // 하체 보통 회원이 구매한 수
    private Integer lowerType3Count;  // 하체 비만 회원이 구매한 수

    public void increaseLikeCount() {
        this.likeCount = this.likeCount + 1;
    }

    public void increaseUpperCount(Integer upperType) {
        switch (upperType) {
            case 1:
                this.upperType1Count = this.upperType1Count + 1;
                break;
            case 2:
                this.upperType2Count = this.upperType2Count + 1;
                break;
            case 3:
                this.upperType3Count = this.upperType3Count + 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid upperType : " + upperType);
        }
    }

    public void increaseLowerCount(Integer lowerType) {
        switch (lowerType) {
            case 1:
                this.lowerType1Count = this.lowerType1Count + 1;
                break;
            case 2:
                this.lowerType2Count = this.lowerType2Count + 1;
                break;
            case 3:
                this.lowerType3Count = this.lowerType3Count + 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid lowerType : " + lowerType);
        }
    }
}
