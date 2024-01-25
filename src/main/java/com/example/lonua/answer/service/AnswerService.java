package com.example.lonua.answer.service;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.answer.exception.AnswerNotFoundException;
import com.example.lonua.answer.model.entity.Answer;
import com.example.lonua.answer.model.request.PatchUpdateAnswerReq;
import com.example.lonua.answer.model.request.PostRegisterAnswerReq;
import com.example.lonua.answer.model.response.GetListAnswerRes;
import com.example.lonua.answer.model.response.PatchUpdateAnswerRes;
import com.example.lonua.answer.model.response.PostRegisterAnswerRes;
import com.example.lonua.answer.repository.AnswerRepository;
import com.example.lonua.common.BaseRes;
import com.example.lonua.question.exception.QuestionNotFoundException;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public BaseRes create(PostRegisterAnswerReq postRegisterAnswerReq) {
        Seller seller = (Seller) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Question> result = questionRepository.findByQuestionIdx(postRegisterAnswerReq.getQuestionIdx());

        if(result.isPresent()) {
            answerRepository.save(Answer.builder()
                    .answerContent(postRegisterAnswerReq.getAnswerContent())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .seller(seller)
                    .question(Question.builder().questionIdx(postRegisterAnswerReq.getQuestionIdx()).build())
                    .build());

            // 답변이 달렸으므로 HasAnswer의 값을 true로 Question DB에 저장한다.
            Question question = result.get();
            question.setHasAnswer(true);
            questionRepository.save(question);


            PostRegisterAnswerRes postRegisterAnswerRes = PostRegisterAnswerRes.builder()
                    .questionIdx(question.getQuestionIdx())
                    .questionType(question.getQuestionType())
                    .questionTitle(question.getQuestionTitle())
                    .questionContent(question.getQuestionContent())
                    .sellerIdx(seller.getSellerIdx())
                    .answerContent(postRegisterAnswerReq.getAnswerContent())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("답변 등록 성공")
                    .result(postRegisterAnswerRes)
                    .build();

        } else {
            throw new QuestionNotFoundException(postRegisterAnswerReq.getQuestionIdx());
        }
    }

    @Transactional
    public BaseRes update(PatchUpdateAnswerReq patchUpdateAnswerReq) {

        Seller seller = (Seller) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Answer> result = answerRepository.findByAnswerIdxAndSeller_SellerIdx(patchUpdateAnswerReq.getAnswerIdx(), seller.getSellerIdx());

        if (result.isPresent()) {
            Answer answer = result.get();
            answer.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            answer.setAnswerContent(patchUpdateAnswerReq.getAnswerContent());

            answerRepository.save(answer);

            PatchUpdateAnswerRes patchUpdateAnswerRes = PatchUpdateAnswerRes.builder()
                    .answerIdx(patchUpdateAnswerReq.getAnswerIdx())
                    .answerContent(patchUpdateAnswerReq.getAnswerContent())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("답변 수정 성공")
                    .result(patchUpdateAnswerRes)
                    .build();
        } else {
            throw new AnswerNotFoundException(patchUpdateAnswerReq.getAnswerIdx());
        }
    }

    @Transactional(readOnly = true)
    public BaseRes list(Integer page, Integer size) {

        Seller seller = (Seller) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Answer> answerList = answerRepository.findAnswerList(pageable, seller.getSellerIdx());

        if(answerList != null) {
            List<GetListAnswerRes> getListAnswerResArrayList = new ArrayList<>();

            for (Answer answer : answerList) {
                GetListAnswerRes getListAnswerRes = GetListAnswerRes.builder()
                        .questionIdx(answer.getQuestion().getQuestionIdx())
                        .questionType(answer.getQuestion().getQuestionType())
                        .questionTitle(answer.getQuestion().getQuestionTitle())
                        .answerIdx(answer.getAnswerIdx())
                        .answerContent(answer.getAnswerContent())
                        .build();

                getListAnswerResArrayList.add(getListAnswerRes);
            }

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListAnswerResArrayList)
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("요청 살패")
                    .result("답변 목록을 찾을 수 없습니다.")
                    .build();
        }
    }
}
