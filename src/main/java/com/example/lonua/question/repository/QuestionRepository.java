package com.example.lonua.question.repository;

import com.example.lonua.question.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
     Optional<Question> findByQuestionIdx(Integer questionIdx);
}
