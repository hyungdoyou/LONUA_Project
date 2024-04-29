package com.example.lonua.answer.repository.querydsl;

import com.example.lonua.seller.model.entity.QSeller;
import com.example.lonua.answer.model.entity.Answer;
import com.example.lonua.answer.model.entity.QAnswer;
import com.example.lonua.question.model.entity.QQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerRepositoryCustomImpl extends QuerydslRepositorySupport implements AnswerRepositoryCustom {
    public AnswerRepositoryCustomImpl() {
        super(Answer.class);
    }

    @Override
    public Page<Answer> findAnswerList(Pageable pageable, Integer sellerIdx) {
        QAnswer answer = new QAnswer("answer");
        QQuestion question = new QQuestion("question");
        QSeller seller = new QSeller("seller");

        List<Answer> result = from(answer)
                .leftJoin(answer.question, question).fetchJoin()
                .leftJoin(answer.seller, seller).fetchJoin()
                .where(seller.sellerIdx.eq(sellerIdx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}


