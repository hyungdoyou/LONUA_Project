package com.example.lonua.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterReviewRes {

    private String reviewContent;
    private String reviewPhoto;
    private Integer evaluation;
}
