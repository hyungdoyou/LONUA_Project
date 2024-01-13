package com.example.lonua.question.service;

import com.example.lonua.config.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.question.model.request.PostQuestionReq;
import com.example.lonua.question.repository.QuestionRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public BaseRes registerQuestion(PostQuestionReq request) {

        questionRepository.save(Question.builder()
                .user(User.builder().userIdx(request.getUser().getUserIdx()).build())
                .product(Product.builder().productIdx(request.getProduct().getProductIdx()).build())
                .questionType(request.getQuestionType())
                .questionContent(request.getQuestionContent())
                .hasAnswer(request.getHasAnswer())
                .isSecret(request.getIsSecret())
                .build());

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("질문 등록 성공")
                .result(request)
                .build();

        return baseRes;

    }

    public BaseRes readQuestion(Integer questionIdx) {
        Optional<Question> result = questionRepository.findByQuestionIdx(questionIdx);
        if (result.isPresent()) {
            Question question = result.get();

            PostQuestionReq.builder()
              .questionIdx(question.getQuestionIdx())
              .questionType(question.getQuestionType())
              .questionContent(question.getQuestionContent())
              .build();

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("질문 불러오기 성공")
                    .result(questionIdx)
                    .build();

            return baseRes;


        } else {
            return null;
        }
    }

    public BaseRes updateQuestion(PostQuestionReq request) {
        Optional<Question> result = questionRepository.findByQuestionIdx(request.getQuestionIdx());
        if (result.isPresent()) {
            Question question = result.get();
            question.setQuestionType(request.getQuestionType());
            question.setQuestionContent(request.getQuestionContent());

            questionRepository.save(question);

            BaseRes baseRes = BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("질문 수정 성공")
                    .result(request)
                    .build();

            return baseRes;
        }

        return null;
    }

    public BaseRes deleteQuestion(Integer questionIdx) {
        questionRepository.delete(Question.builder().questionIdx(questionIdx).build());

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("질문 삭제 성공")
                .result(questionIdx)
                .build();

        return baseRes;
    }

}
