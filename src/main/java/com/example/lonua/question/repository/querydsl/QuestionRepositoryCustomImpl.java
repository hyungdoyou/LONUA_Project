package com.example.lonua.question.repository.querydsl;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductImage;
import com.example.lonua.question.model.entity.QQuestion;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.user.model.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionRepositoryCustomImpl extends QuerydslRepositorySupport implements QuestionRepositoryCustom {

    public QuestionRepositoryCustomImpl() {
        super(Question.class);
    }

    @Override
    public Page<Question> findQuestionList(Pageable pageable, Integer idx) {
        QQuestion question = new QQuestion("question");
        QProduct product = new QProduct("product");
        QProductImage productImage = new QProductImage("productImage");
        QUser user = new QUser("user");

        List<Question> result = from(question)
                .leftJoin(question.product, product).fetchJoin()
                .leftJoin(product.productImageList, productImage).fetchJoin()
                .leftJoin(question.user, user).fetchJoin()
                .where(user.userIdx.eq(idx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}
