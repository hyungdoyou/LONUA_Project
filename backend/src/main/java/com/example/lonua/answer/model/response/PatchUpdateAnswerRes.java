package com.example.lonua.answer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchUpdateAnswerRes {
    private Integer answerIdx;
    private String answerContent;
}
