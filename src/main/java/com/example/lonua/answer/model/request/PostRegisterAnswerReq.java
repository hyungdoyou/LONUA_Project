package com.example.lonua.answer.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterAnswerReq {

    @NotNull(message = "질문 IDX는 필수 입력항목입니다.")
    @Min(value = 1, message = "질문 IDX는 최소 1 이상이어야 합니다.")
    @ApiModelProperty(value = "질문 IDX", example = "1", required = true)
    private Integer questionIdx;

    @NotNull(message = "답변 내용은 필수 입력항목입니다.")
    @Length(min=10, max=500, message = "답변 내용은 최소 10글자 이상, 최대 500자 이하여야 합니다.")
    @ApiModelProperty(value = "답변 내용", example = "질문에 대한 답변은 아래와 같습니다.", required = true)
    private String answerContent;
}
