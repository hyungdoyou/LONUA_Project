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
public class PostRegisterBranchReq {

    @NotNull(message = "브랜드 고유번호는 필수 입력항목입니다.")
    @Min(value = 1, message = "브랜드 고유번호는 1이상의 숫자여야 합니다.")
    @ApiModelProperty(value = "브랜드Idx", example = "1", required = true)
    private Integer brandIdx;

    @NotNull(message = "지점명은 필수 입력항목입니다.")
    @Length(max = 30, message = "지점명은 최대 30글자 이하여야 합니다.")
    @ApiModelProperty(value = "추가할 지점 이름", example = "강남점", required = true)
    private String branchName;

    @NotNull(message = "지점주소는 필수 입력항목입니다.")
    @Length(max = 100, message = "지점주소는 최대 100글자 이하여야 합니다.")
    @ApiModelProperty(value = "추가할 지점 주소", example = "서울시 강남구", required = true)
    private String branchAddress;
}
