package com.example.lonua.grade.repository;

import com.example.lonua.grade.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    public Optional<Grade> findByGradeType(String gradeType);
}
