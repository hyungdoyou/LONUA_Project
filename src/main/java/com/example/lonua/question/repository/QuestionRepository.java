package com.example.lonua.question.repository;

import com.example.lonua.question.model.entity.Question;
import com.example.lonua.question.repository.querydsl.QuestionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer>, QuestionRepositoryCustom {
     Optional<Question> findByQuestionIdxAndUser_userIdx(Integer questionIdx, Integer userIdx);

     Integer deleteByQuestionIdxAndUser_userIdx(Integer questionIdx, Integer userIdx);

     Optional<Question> findByQuestionIdx(Integer questionIdx);

}
