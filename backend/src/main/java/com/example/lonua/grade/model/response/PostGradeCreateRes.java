package com.example.lonua.grade.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostGradeCreateRes {
    private Integer gradeIdx;
    private String gradeType;
    private Integer discountRate;
}
