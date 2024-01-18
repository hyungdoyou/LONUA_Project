package com.example.lonua.grade.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetGradeReadRes {
    private Integer gradeIdx;
    private String gradeType;
    private Integer discountRate;
}
