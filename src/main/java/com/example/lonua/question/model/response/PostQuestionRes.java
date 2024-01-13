package com.example.lonua.question.model.response;


import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostQuestionRes {

    private Product product;
    private User user;
    private String questionType;
    private String questionContent;
    private String hasAnswer;
    private String isSecret;
}
