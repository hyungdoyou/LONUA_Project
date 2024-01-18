package com.example.lonua.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListReviewRes {

    private String reviewContent;
    private String reviewPhoto;
    private Integer evaluation;
    private String updatedAt;

    private String productName;
    private String productImage;
}
