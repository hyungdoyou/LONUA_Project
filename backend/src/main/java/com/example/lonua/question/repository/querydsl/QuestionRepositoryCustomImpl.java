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

    @Override
    public Page<Question> findProductQuestionList(Pageable pageable, Integer productIdx) {
        QQuestion question = new QQuestion("question");
        QProduct product = new QProduct("product");
        QUser user = new QUser("user");

        List<Question> result = from(question)
                .leftJoin(question.user, user).fetchJoin()
                .leftJoin(question.product, product).fetchJoin()
                .where(question.product.productIdx.eq(productIdx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().collect(Collectors.toList());   // where 조건문으로 찾을때는 distinct() 를 빼줘야함 ( 이미 중복되는것이 사라졌기 때문에 )

        return new PageImpl<>(result, pageable, result.size());
    }
}
