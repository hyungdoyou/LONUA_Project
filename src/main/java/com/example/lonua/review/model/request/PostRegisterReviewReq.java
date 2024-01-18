package com.example.lonua.review.model.request;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterReviewReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "상품 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer productIdx;

    @NotNull
    @Length(min=1, max=200)
    @ApiModelProperty(value = "리뷰내용(200자 이하)", example = "색깔도 예쁘고 너무 맘에 들어요", required = true)
    private String reviewContent;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "평점", example = "1", required = true)
    private Integer evaluation;

}
