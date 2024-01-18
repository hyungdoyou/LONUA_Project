package com.example.lonua.category.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUpdateCategoryReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "카테고리 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer categoryIdx;

    @NotNull
    @Pattern(regexp = "^[\\p{IsHangul}]+$")
    @ApiModelProperty(value = "카테고리명", example = "아우터", required = true)
    private String categoryName;
}
