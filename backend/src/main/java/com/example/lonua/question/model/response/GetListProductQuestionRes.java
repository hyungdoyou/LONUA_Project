package com.example.lonua.question.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetListProductQuestionRes {

    private Integer questionIdx;
    private String userName;
    private String questionType;
    private String questionTitle;
    private String questionContent;
    private String updatedAt;
    private Boolean hasAnswer;
    private Boolean isSecret;
}
