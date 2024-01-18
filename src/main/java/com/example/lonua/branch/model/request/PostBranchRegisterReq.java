package com.example.lonua.branch.model.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostBranchRegisterReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "브랜드Idx", example = "1", required = true)
    private Integer brandIdx;

    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "추가할 지점 이름", example = "강남점", required = true)
    private String branchName;

    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "추가할 지점 주소", example = "서울시 강남구", required = true)
    private String branchAddress;
}
