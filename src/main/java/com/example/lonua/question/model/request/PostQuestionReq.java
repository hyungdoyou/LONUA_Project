package com.example.lonua.question.model.request;


import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostQuestionReq {

    private Integer questionIdx;
    private Product product;
    private User user;
    private String questionType;
    private String questionContent;
    private String hasAnswer;
    private String isSecret;
}
