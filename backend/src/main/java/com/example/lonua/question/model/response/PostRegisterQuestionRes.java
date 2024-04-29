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
public class PostRegisterQuestionRes {

    private String questionType;
    private String questionTitle;
    private String questionContent;
    private Boolean isSecret;

}
