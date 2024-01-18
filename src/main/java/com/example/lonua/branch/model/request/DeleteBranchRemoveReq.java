package com.example.lonua.branch.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBranchRemoveReq {

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "삭제할 지점Idx", example = "1", required = true)
    private Integer branchIdx;
}
