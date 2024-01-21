package com.example.lonua.answer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterAnswerRes {

    private Integer questionIdx;
    private String questionType;
    private String questionTitle;
    private String questionContent;
    private Integer sellerIdx;
    private String answerContent;
}
