package com.example.lonua.reply.model.request;

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
public class PostReplyRegisterReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "브랜드Idx", example = "1", required = true)
    private Integer brandIdx;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "답변Idx", example = "1", required = true)
    private Integer questionIdx;

    @NotNull
    @Length(max=500)
    @ApiModelProperty(value = "답변 내용", example = "안녕하세요. xxx점 누구누구 입니다.", required = true)
    private String replyContent;
}
