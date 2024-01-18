package com.example.lonua.reply.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReplyListReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "브랜드Idx", example = "1", required = true)
    private Integer brandIdx;
}
