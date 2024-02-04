package com.example.lonua.product.model.entity;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.likes.model.entity.Likes.Likes;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.OrdersProduct;
import com.example.lonua.product.model.request.PatchUpdateProductReq;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.review.model.entity.Review;
import com.example.lonua.style.model.entity.Style;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Likes> likesList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductIntrodImage> productIntrodImageList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrdersProduct> ordersProductList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Brand_idx")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_idx")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Style_idx")
    private Style style;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ProductCount productCount;

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer salePrice;

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

    public void update(PatchUpdateProductReq patchUpdateProductReq) {
        if (patchUpdateProductReq.getProductName() != null) {
            this.productName = patchUpdateProductReq.getProductName();
        }
        if (patchUpdateProductReq.getQuantity() != null) {
            this.quantity = patchUpdateProductReq.getQuantity();
        }
        if (patchUpdateProductReq.getPrice() != null) {
            this.price = patchUpdateProductReq.getPrice();
        }
        if (patchUpdateProductReq.getSalePrice() != null) {
            this.salePrice = patchUpdateProductReq.getSalePrice();
        }
    }
}
