package com.example.lonua.question.service;

import com.example.lonua.common.BaseRes;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.question.exception.QuestionNotFoundException;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.question.model.request.PatchUpdateQuestionReq;
import com.example.lonua.question.model.request.PostRegisterQuestionReq;
import com.example.lonua.question.model.response.GetListProductQuestionRes;
import com.example.lonua.question.model.response.GetListQuestionRes;
import com.example.lonua.question.model.response.PostRegisterQuestionRes;
import com.example.lonua.question.repository.QuestionRepository;
import com.example.lonua.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = false)
    public BaseRes register(User user, PostRegisterQuestionReq postRegisterQuestionReq) {

        Question question = Question.builder()
                .product(Product.builder().productIdx(postRegisterQuestionReq.getProductIdx()).build())
                .user(user)
                .questionType(postRegisterQuestionReq.getQuestionType())
                .questionTitle(postRegisterQuestionReq.getQuestionTitle())
                .questionContent(postRegisterQuestionReq.getQuestionContent())
                .hasAnswer(false)
                .isSecret(postRegisterQuestionReq.getIsSecret())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .build();
        questionRepository.save(question);

        PostRegisterQuestionRes postRegisterQuestionRes = PostRegisterQuestionRes.builder()
                .questionType(postRegisterQuestionReq.getQuestionType())
                .questionTitle(postRegisterQuestionReq.getQuestionTitle())
                .questionContent(postRegisterQuestionReq.getQuestionContent())
                .isSecret(postRegisterQuestionReq.getIsSecret())
                .build();

        BaseRes baseRes = BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("질문 등록 성공")
                .result(postRegisterQuestionRes)
                .build();

        return baseRes;
    }

    // 질문 목록 조회
    @Transactional(readOnly = true)
    public BaseRes listproductQnA(Integer productIdx, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Question> questionList = questionRepository.findProductQuestionList(pageable, productIdx);

        if(questionList != null) {
            List<GetListProductQuestionRes> getListProductQuestionResList = new ArrayList<>();
            for(Question question : questionList) {

            GetListProductQuestionRes getListProductQuestionRes = GetListProductQuestionRes.builder()
                    .questionIdx(question.getQuestionIdx())
                    .userName(question.getUser().getName())
                    .questionType(question.getQuestionType())
                    .questionTitle(question.getQuestionTitle())
                    .questionContent(question.getQuestionContent())
                    .updatedAt(question.getUpdatedAt())
                    .hasAnswer(question.getHasAnswer())
                    .isSecret(question.getIsSecret())
                    .build();

                getListProductQuestionResList.add(getListProductQuestionRes);
        }

            return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(getListProductQuestionResList)
                .build();
        } else {
            return BaseRes.builder()
                .code(400)
                .isSuccess(false)
                .message("요청 실패")
                .result("상품에 해당하는 질문을 찾을 수 없습니다.")
                .build();
        }
    }

    // 상품 상세 페이지 질문 목록 조회
    @Transactional(readOnly = true)
    public BaseRes list(User user, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page-1, size);

        Page<Question> questionList = questionRepository.findQuestionList(pageable, user.getUserIdx());

        if(questionList != null) {
            List<GetListQuestionRes> getListQuestionResList = new ArrayList<>();
            for(Question question : questionList) {

                GetListQuestionRes getListQuestionRes = GetListQuestionRes.builder()
                        .questionIdx(question.getQuestionIdx())
                        .productIdx(question.getProduct().getProductIdx())
                        .brandName(question.getProduct().getBrand().getBrandName())
                        .productName(question.getProduct().getProductName())
                        .productImage(question.getProduct().getProductImageList().get(0).getProductImage())
                        .questionType(question.getQuestionType())
                        .questionTitle(question.getQuestionTitle())
                        .questionContent(question.getQuestionContent())
                        .createdAt(question.getCreatedAt())
                        .hasAnswer(question.getHasAnswer())
                        .isSecret(question.getIsSecret())
                        .build();

                getListQuestionResList.add(getListQuestionRes);
            }

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListQuestionResList)
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 실패")
                    .result("질문을 찾을 수 없습니다.")
                    .build();
        }
    }
    @Transactional(readOnly = false)
    public BaseRes update(User user, PatchUpdateQuestionReq patchUpdateQuestionReq) {
        Optional<Question> result = questionRepository.findByQuestionIdxAndUser_userIdx(patchUpdateQuestionReq.getQuestionIdx(), user.getUserIdx());

        if (result.isPresent()) {
            Question question = result.get();
            if(question.getHasAnswer() != true) {
                question.update(patchUpdateQuestionReq);
                question.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
                questionRepository.save(question);

                return BaseRes.builder()
                        .code(200)
                        .isSuccess(true)
                        .message("질문 수정 성공")
                        .result(patchUpdateQuestionReq)
                        .build();
            } else {
                return BaseRes.builder()
                        .code(400)
                        .isSuccess(false)
                        .message("질문을 수정할 수 없습니다.")
                        .result("질문에 대한 답변이 등록되어 수정할 수 없습니다.")
                        .build();
            }

        } else {
            throw new QuestionNotFoundException(patchUpdateQuestionReq.getQuestionIdx());
        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer questionIdx, User user) {
        Integer result = questionRepository.deleteByQuestionIdxAndUser_userIdx(questionIdx, user.getUserIdx());

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("질문 삭제 성공")
                    .result("요청 성공")
                    .build();
        } else {
            throw new QuestionNotFoundException(questionIdx);
        }
    }
}
