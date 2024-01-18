package com.example.lonua.grade.model.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostGradeCreateReq {
    @NotNull
    @Length(max=20)
    @ApiModelProperty(value = "등록할 등급의 이름", example = "골드", required = true)
    private String gradeType;


    @NotNull
    @Range(min = 1, max = 10)
    @ApiModelProperty(value = "등급의 할인율", example = "5", required = true)
    private Integer discountRate;
}
