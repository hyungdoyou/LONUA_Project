package com.example.lonua.branch.service;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.branch.model.request.PostRegisterReq;
import com.example.lonua.branch.repository.BranchRepository;
import com.example.lonua.config.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public BaseRes create(PostRegisterReq request) {

        Branch branch = branchRepository.save(Branch.builder().build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostRegisterReq.builder()
                        .branchName(branch.getBranchName())
                        .branchAddress(branch.getBranchAddress())
                        .build())
                .build();
    } public void read() {
        
    } public void list() {

    } public void delete() {

    } public void update() {

    }
}
