package com.example.lonua.grade.service;

import com.example.lonua.common.BaseRes;
import com.example.lonua.grade.model.request.PatchGradeUpdateReq;
import com.example.lonua.grade.model.request.PostGradeCreateReq;
import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.grade.model.response.GetGradeListRes;
import com.example.lonua.grade.model.response.PatchGradeUpdateRes;
import com.example.lonua.grade.model.response.PostGradeCreateRes;
import com.example.lonua.grade.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    @Transactional(readOnly = false)
    public BaseRes create(PostGradeCreateReq postGradeCreateReq) {

        Grade grade = gradeRepository.save(Grade.builder()
                .gradeType(postGradeCreateReq.getGradeType())
                .discountRate(postGradeCreateReq.getDiscountRate())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(PostGradeCreateRes.builder()
                        .gradeIdx(grade.getGradeIdx())
                        .discountRate(grade.getDiscountRate())
                        .gradeType(grade.getGradeType())
                        .build())
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes list() {
        List<Grade> all = gradeRepository.findAll();
        List<GetGradeListRes> getListResGradeList = new ArrayList<>();
        for (Grade grade : all) {
            GetGradeListRes build = GetGradeListRes.builder()
                    .gradeIdx(grade.getGradeIdx())
                    .gradeType(grade.getGradeType())
                    .discountRate(grade.getDiscountRate())
                    .build();
            getListResGradeList.add(build);
        }

            return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청성공")
                .result(getListResGradeList)
                .build();
    }

//    public BaseRes read(GetReadReq request) {
//        Optional<Grade> byGradeType = gradeRepository.findByGradeType(request.getGradeType());
//        if (byGradeType.isPresent()) {
//            Grade grade = byGradeType.get();
//            return BaseRes.builder()
//                    .code(200)
//                    .isSuccess(true)
//                    .message("요청성공")
//                    .result(GetReadRes.builder()
//                            .gradeIdx(grade.getGradeIdx())
//                            .gradeType(grade.getGradeType())
//                            .discountRate(grade.getDiscountRate())
//                            .build())
//                    .build();
//        }
//        return null;
//    }

    @Transactional(readOnly = false)
    public BaseRes update(PatchGradeUpdateReq request) {
        Optional<Grade> byId = gradeRepository.findById(request.getGradeIdx());

        if (byId.isPresent()) {
            Grade grade = byId.get();
            grade.setGradeType(request.getGradeType());
            grade.setDiscountRate(request.getDiscountRate());
            Grade result = gradeRepository.save(grade);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청성공")
                    .result(PatchGradeUpdateRes.builder()
                            .gradeIdx(result.getGradeIdx())
                            .gradeType(result.getGradeType())
                            .discountRate(result.getDiscountRate())
                            .build())
                    .build();
        }
        return null;
    }

}
