package com.example.lonua.answer.repository;

import com.example.lonua.answer.model.entity.Answer;
import com.example.lonua.answer.repository.querydsl.AnswerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer>, AnswerRepositoryCustom {


    List<Answer> findAllBySeller_SellerIdx(Integer sellerIdx);


    Optional<Answer> findByAnswerIdxAndSeller_SellerIdx(Integer answerIdx, Integer sellerIdx);
}
