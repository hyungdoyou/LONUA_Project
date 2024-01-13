package com.example.lonua.grade.service;

import com.example.lonua.branch.model.entity.Branch;
import com.example.lonua.config.BaseRes;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.GradeException;
import com.example.lonua.grade.model.request.PostCreateReq;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.grade.model.response.GetListRes;
import com.example.lonua.grade.model.response.PostCreateRes;
import com.example.lonua.grade.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public BaseRes create(PostCreateReq postCreateReq) {

        Grade grade = gradeRepository.save(Grade.builder()
                .gradeType(postCreateReq.getGradeType())
                .discountRate(postCreateReq.getDiscountRate())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostCreateRes.builder()
                        .gradeIdx(grade.getGradeIdx())
                        .discountRate(grade.getDiscountRate())
                        .gradeType(grade.getGradeType())
                        .build())
                .build();
    }

    public BaseRes list() {
        List<Grade> all = gradeRepository.findAll();
        List<GetListRes> getListResList = new ArrayList<>();
        for (Grade grade : all) {
            GetListRes build = GetListRes.builder()
                    .gradeIdx(grade.getGradeIdx())
                    .gradeType(grade.getGradeType())
                    .discountRate(grade.getDiscountRate())
                    .build();
            getListResList.add(build);
        }

            return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResList)
                .build();
    }
}
