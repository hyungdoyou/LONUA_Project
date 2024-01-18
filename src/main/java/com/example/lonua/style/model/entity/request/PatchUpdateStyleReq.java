package com.example.lonua.style.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUpdateStyleReq {

    @NotNull
    @Positive
    @ApiModelProperty(value = "스타일 IDX", example = "1", required = true)
    private Integer styleIdx;

    @NotNull
    @Length(max=7)
    @Pattern(regexp = "^[가-힣]*$")
    @ApiModelProperty(value = "스타일 유형", example = "댄디", required = true)
    private String StyleType;
}
