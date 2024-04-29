package com.example.lonua.category.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterCategoryReq {

    @NotNull
    @Pattern(regexp = "^[\\p{IsHangul}]+$")
    @ApiModelProperty(value = "카테고리명", example = "아우터", required = true)
    private String categoryName;

}
