package com.example.lonua.product.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCountIdx;

    @OneToOne
    @JoinColumn(name = "Product_idx")
    private Product product;

    private Integer likeCount; // 상품 좋아요 수

    private Integer upperType1Count;  // 상체 마름 회원이 구매한 수
    private Integer upperType2Count;  // 상체 보통 회원이 구매한 수
    private Integer upperType3Count;  // 상체 비만 회원이 구매한 수

    private Integer lowerType1Count;  // 하체 마름 회원이 구매한 수
    private Integer lowerType2Count;  // 하체 보통 회원이 구매한 수
    private Integer lowerType3Count;  // 하체 비만 회원이 구매한 수

    public void increaseLikeCount() {
        this.likeCount = this.likeCount + 1;
    }

    public void decreaseLikeCount() {
        this.likeCount = this.likeCount - 1;
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
