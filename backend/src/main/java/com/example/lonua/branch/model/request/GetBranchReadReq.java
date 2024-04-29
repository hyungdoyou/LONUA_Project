package com.example.lonua.branch.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetBranchReadReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "해당 브랜드Idx", example = "1", required = true)
    private Integer brandIdx;

    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "찾고 싶은 지점 이름", example = "강남점", required = true)
    private String branchName;
}
