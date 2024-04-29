package com.example.lonua.branch.service;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.branch.model.request.*;
import com.example.lonua.branch.model.response.GetBranchListRes;
import com.example.lonua.branch.model.response.GetBranchReadRes;
import com.example.lonua.branch.model.response.PatchBranchUpdateRes;
import com.example.lonua.branch.model.response.PostBranchRegisterRes;
import com.example.lonua.branch.repository.BranchRepository;
import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.common.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    @Transactional(readOnly = false)
    public BaseRes create(PostRegisterBranchReq request) {
        Branch branch = branchRepository.save(Branch.builder()
                .brand(Brand.builder()
                        .brandIdx(request.getBrandIdx())
                        .build())
                .branchName(request.getBranchName())
                .branchAddress(request.getBranchAddress())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostBranchRegisterRes.builder()
                        .branchIdx(branch.getBranchIdx())
                        .branchName(branch.getBranchName())
                        .branchAddress(branch.getBranchAddress())
                        .brandIdx(branch.getBrand().getBrandIdx())
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes read(GetBranchReadReq request) {
        Optional<Branch> bybranchName = branchRepository.findByBranchNameAndBrandBrandIdx(request.getBranchName(), request.getBrandIdx());
        if (bybranchName.isPresent()) {
            Branch branch = bybranchName.get();
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .result(GetBranchReadRes.builder()
                            .branchIdx(branch.getBranchIdx())
                            .branchName(branch.getBranchName())
                            .branchAddress(branch.getBranchAddress())
                            .brandIdx(branch.getBrand().getBrandIdx())
                            .build())
                    .build();
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(false)
                .message("요청 실패")
                .result("잘못된 요청입니다.")
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes list(GetBranchListReq request) {
        List<Branch> all = branchRepository.findAllByBrandBrandIdx(request.getBrandIdx());
        List<GetBranchListRes> getListResBranchList = new ArrayList<>();
        for (Branch branch : all) {
            GetBranchListRes getBranchListRes = GetBranchListRes.builder()
                    .branchIdx(branch.getBranchIdx())
                    .branchName(branch.getBranchName())
                    .branchAddress(branch.getBranchAddress())
                    .brandIdx(branch.getBrand().getBrandIdx())
                    .build();
            getListResBranchList.add(getBranchListRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResBranchList)
                .build();
    }

    @Transactional(readOnly = false)
    public BaseRes delete(DeleteBranchRemoveReq request) {

        Branch branch = Branch.builder()
                .branchIdx(request.getBranchIdx())
                .build();

        branchRepository.delete(branch);

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result("지점을 삭제 했습니다.")
                .build();
    }

    @Transactional(readOnly = false)
    public BaseRes update(PatchBranchUpdateReq request) {
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
                    .result(PatchBranchUpdateRes.builder()
                            .branchIdx(result.getBranchIdx())
                            .branchName(result.getBranchName())
                            .branchAddress(result.getBranchAddress())
                            .brandIdx(result.getBrand().getBrandIdx())
                            .build())
                    .build();
        }
        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result("지점IDX가 존재하지 않습니다.")
                .build();
    }
}
