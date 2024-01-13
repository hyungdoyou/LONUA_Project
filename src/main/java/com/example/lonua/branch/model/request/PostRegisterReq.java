package com.example.lonua.branch.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRegisterReq {
    private Integer branchIdx;
    private String branchName;
    private String branchAddress;
}
