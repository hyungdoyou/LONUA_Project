package com.example.lonua.branch.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostRegisterRes {
    private String branchName;
    private String branchAddress;
}
