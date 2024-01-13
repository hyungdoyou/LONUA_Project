package com.example.lonua.branch.service;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.branch.model.request.PostRegisterReq;
import com.example.lonua.branch.model.response.PostRegisterRes;
import com.example.lonua.branch.repository.BranchRepository;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.config.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public BaseRes create(PostRegisterReq request) {

        Branch branch = branchRepository.save(Branch.builder()
                .brand(Brand.builder()
                        .brandIdx(request.getBranchIdx())
                        .build())
                .branchName(request.getBranchName())
                .branchAddress(request.getBranchAddress())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostRegisterRes.builder()
                        .branchIdx(branch.getBranchIdx())
                        .branchName(branch.getBranchName())
                        .branchAddress(branch.getBranchAddress())
                        .brandIdx(branch.getBrand().getBrandIdx())
                        .build())
                .build();
    }

    public BaseRes read() {


        return null;
    }

    public void list() {

    }

    public void delete() {

    }

    public void update() {

    }
}
