package com.example.lonua.grade.model.entity;

import com.example.lonua.user.model.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeIdx;

    @Column(nullable = false, length = 45)
    private String gradeType;

    @Column(nullable = false)
    private Integer discountRate;

    @OneToMany(mappedBy = "grade", fetch = FetchType.EAGER)
    private List<User> userList = new ArrayList<>();
}
