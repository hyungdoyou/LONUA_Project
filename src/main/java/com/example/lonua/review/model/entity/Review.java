package com.example.lonua.review.model.entity;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.review.model.request.PatchUpdateReviewReq;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @Column(nullable = false, length = 500)
    private String reviewContent;

    @Column(length = 500)
    private String reviewPhoto;

    @Column(nullable = false)
    private Integer evaluation;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    public void update(PatchUpdateReviewReq patchUpdateReviewReq, String reviewFileName) {
        if(patchUpdateReviewReq.getReviewContent() != null) {
            this.reviewContent = patchUpdateReviewReq.getReviewContent();
        }

        if(patchUpdateReviewReq.getEvaluation() != null) {
            this.evaluation = patchUpdateReviewReq.getEvaluation();

        }
        if (reviewFileName != null) {
            this.reviewPhoto = reviewFileName;
        }
    }
}
