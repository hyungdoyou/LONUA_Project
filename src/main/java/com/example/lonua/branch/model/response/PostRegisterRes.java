package com.example.lonua.branch.model.response;

import com.example.lonua.brand.model.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostRegisterRes {
    private Integer branchIdx;
    private String branchName;
    private String branchAddress;
    private Integer brandIdx;
}
