package com.example.lonua.branch.service;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.branch.model.request.DeleteRemoveReq;
import com.example.lonua.branch.model.request.GetReadReq;
import com.example.lonua.branch.model.request.PatchUpdateReq;
import com.example.lonua.branch.model.request.PostRegisterReq;
import com.example.lonua.branch.model.response.GetListRes;
import com.example.lonua.branch.model.response.GetReadRes;
import com.example.lonua.branch.model.response.PatchUpdateRes;
import com.example.lonua.branch.model.response.PostRegisterRes;
import com.example.lonua.branch.repository.BranchRepository;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.config.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public BaseRes read(GetReadReq request) {
        Optional<Branch> bybranchName = branchRepository.findBybranchName(request.getBranchName());
        if (bybranchName.isPresent()) {
            Branch branch = bybranchName.get();
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .result(GetReadRes.builder()
                            .branchIdx(branch.getBranchIdx())
                            .branchName(branch.getBranchName())
                            .branchAddress(branch.getBranchAddress())
                            .brandIdx(branch.getBrand().getBrandIdx())
                            .build())
                    .build();
        }
        return null;
    }

    public BaseRes list() {
        List<Branch> all = branchRepository.findAll();
        List<GetListRes> getListResList = new ArrayList<>();
        for (Branch branch : all) {
            GetListRes getListRes = GetListRes.builder()
                    .branchIdx(branch.getBranchIdx())
                    .branchName(branch.getBranchName())
                    .branchAddress(branch.getBranchAddress())
                    .brandIdx(branch.getBrand().getBrandIdx())
                    .build();
            getListResList.add(getListRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResList)
                .build();
    }

    public BaseRes delete(DeleteRemoveReq request) {

        Branch branch = Branch.builder()
                .branchIdx(request.getBranchIdx())
                .build();

        branchRepository.delete(branch);

        return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .build();
    }

    public BaseRes update(PatchUpdateReq request) {
        Optional<Branch> bybranchName = branchRepository.findById(request.getBranchIdx());

        if (bybranchName.isPresent()) {
            Branch branch = bybranchName.get();
            branch.setBranchName(request.getBranchName());
            branch.setBranchAddress(request.getBranchAddress());

            Branch result = branchRepository.save(branch);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .result(PatchUpdateRes.builder()
                            .branchIdx(result.getBranchIdx())
                            .branchName(result.getBranchName())
                            .branchAddress(result.getBranchAddress())
                            .brandIdx(result.getBrand().getBrandIdx())
                            .build())
                    .build();
        }
        return null;
    }
}
