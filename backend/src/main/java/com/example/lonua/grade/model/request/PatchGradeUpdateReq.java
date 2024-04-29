package com.example.lonua.grade.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PatchGradeUpdateReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "수정할 등급의 idx", example = "1", required = true)
    private Integer gradeIdx;


    @NotNull
    @Length(max=20)
    @ApiModelProperty(value = "수정값 : 등급의 이름", example = "골드", required = true)
    private String gradeType;


    @NotNull
    @Range(min = 1, max = 100)
    @ApiModelProperty(value = "수정값 : 등급의 할인율", example = "5", required = true)
    private Integer discountRate;;
}
