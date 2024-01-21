package com.example.lonua.answer.repository.querydsl;

import com.example.lonua.answer.model.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerRepositoryCustom {

    Page<Answer> findAnswerList(Pageable pageable, Integer sellerIdx);
}
