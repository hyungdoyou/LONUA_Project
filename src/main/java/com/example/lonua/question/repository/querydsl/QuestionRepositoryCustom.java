package com.example.lonua.question.repository.querydsl;

import com.example.lonua.question.model.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface QuestionRepositoryCustom {

    Page<Question> findQuestionList(Pageable pageable, Integer idx);

    Page<Question> findProductQuestionList(Pageable pageable, Integer productIdx);
}
