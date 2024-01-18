package com.example.lonua.review.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchUpdateReviewReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "리뷰 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer reviewIdx;

    @NotNull
    @Length(min=1, max=200)
    @ApiModelProperty(value = "리뷰내용(200자 이하)", example = "색깔도 예쁘고 너무 맘에 들어요", required = true)
    private String reviewContent;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "평점", example = "1", required = true)
    private Integer evaluation;

}
