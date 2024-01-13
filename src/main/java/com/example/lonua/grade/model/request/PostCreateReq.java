package com.example.lonua.grade.model.request;


import com.example.lonua.user.model.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateReq {
    private String gradeType;
    private Integer discountRate;
}
