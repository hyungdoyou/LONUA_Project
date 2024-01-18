package com.example.lonua.likes.model.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCancelLikesReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "좋아요 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer likesIdx;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;
}
