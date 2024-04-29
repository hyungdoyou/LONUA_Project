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

    @NotNull(message = "좋아요 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "좋아요 IDX는 1이상의 양수입니다.")
    @ApiModelProperty(value = "좋아요 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer likesIdx;

    @NotNull(message = "상품 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "상품 IDX는 1이상의 양수입니다.")
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;
}
