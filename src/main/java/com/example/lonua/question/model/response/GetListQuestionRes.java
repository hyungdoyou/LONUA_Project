package com.example.lonua.question.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListQuestionRes {

    private Integer questionIdx;
    private Integer productIdx;
    private String brandName;
    private String productName;
    private String productImage;

    private String questionType;
    private String questionTitle;
    private String questionContent;
    private String createdAt;
    private Boolean hasAnswer;
    private Boolean isSecret;
}
