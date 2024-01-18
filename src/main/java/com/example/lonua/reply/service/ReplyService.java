package com.example.lonua.reply.service;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.common.BaseRes;
import com.example.lonua.question.model.entity.Question;
import com.example.lonua.question.repository.QuestionRepository;
import com.example.lonua.reply.model.entity.Reply;
import com.example.lonua.reply.model.request.GetReplyListReq;
import com.example.lonua.reply.model.request.PostReplyRegisterReq;
import com.example.lonua.reply.model.request.PatchReplyUpdateReq;
import com.example.lonua.reply.model.response.GetReplyListRes;
import com.example.lonua.reply.model.response.PatchReplyUpdateRes;
import com.example.lonua.reply.model.response.PostReplyRegisterRes;
import com.example.lonua.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = false)
    public BaseRes create(PostReplyRegisterReq request) {
        Reply reply = replyRepository.save(Reply.builder()
                .replyContent(request.getReplyContent())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                .brand(Brand.builder()
                        .brandIdx(request.getBrandIdx())
                        .build())
                .question(Question.builder()
                        .questionIdx(request.getQuestionIdx())
                        .build())
                .build());

        // 답변이 달렸으므로 HasAnswer의 값을 true로 Question DB에 저장한다.
        Optional<Question> byId = questionRepository.findById(request.getQuestionIdx());
        if (byId.isPresent()) {
            Question question = byId.get();
            question.setHasAnswer(true);
            questionRepository.save(question);
        }

        PostReplyRegisterRes postReplyRegisterRes = PostReplyRegisterRes.builder()
                .replyIdx(reply.getReplyIdx())
                .replyContent(reply.getReplyContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .brandIdx(reply.getBrand().getBrandIdx())
                .questionIdx(reply.getQuestion().getQuestionIdx())
                .build();

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(postReplyRegisterRes)
                .build();
    }

    @Transactional(readOnly = false)
    public BaseRes update(PatchReplyUpdateReq request) {

        Optional<Reply> byId = replyRepository.findById(request.getReplyIdx());

        if (byId.isPresent()) {
            Reply reply = byId.get();
            reply.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            reply.setReplyContent(request.getReplyContent());

            Reply saved = replyRepository.save(reply);

            PatchReplyUpdateRes patchReplyUpdateRes = PatchReplyUpdateRes.builder()
                    .replyIdx(saved.getReplyIdx())
                    .replyContent(saved.getReplyContent())
                    .createdAt(saved.getCreatedAt())
                    .updatedAt(saved.getUpdatedAt())
                    .brandIdx(saved.getBrand().getBrandIdx())
                    .questionIdx(saved.getQuestion().getQuestionIdx())
                    .build();

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(patchReplyUpdateRes)
                    .build();
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result("해당 아이디로 조회되는 답변글이 없습니다.")
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes list(GetReplyListReq request) {


        List<Reply> all = replyRepository.findAllByBrandBrandIdx(request.getBrandIdx());
        List<GetReplyListRes> result = new ArrayList<>();

        for (Reply reply : all) {
            GetReplyListRes getReplyListRes = GetReplyListRes.builder()
                    .replyIdx(reply.getReplyIdx())
                    .replyContent(reply.getReplyContent())
                    .createdAt(reply.getCreatedAt())
                    .updatedAt(reply.getUpdatedAt())
                    .brandIdx(reply.getBrand().getBrandIdx())
                    .questionIdx(reply.getQuestion().getQuestionIdx())
                    .build();
            result.add(getReplyListRes);
        }

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("요청 성공")
                .result(result)
                .build();
    }



















}
