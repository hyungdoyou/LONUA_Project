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
public class PatchBranchUpdateReq {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "지점Idx", example = "1", required = true)
    private Integer branchIdx;

    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "수정할 지점명", example = "신대방삼거리점", required = true)
    private String branchName;

    @NotNull
    @Length(max = 50)
    @ApiModelProperty(value = "수정할 지점 주소", example = "서울시 동작구", required = true)
    private String branchAddress;
}
