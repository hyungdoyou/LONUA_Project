package com.example.lonua.question.model.entity;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.question.model.request.PatchUpdateQuestionReq;
import com.example.lonua.answer.model.entity.Answer;
import com.example.lonua.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_idx")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idx")
    private User user;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    @Column(nullable = false, length = 7)
    private String questionType;

    @Column(nullable = false, length = 45)
    private String questionTitle;

    @Column(nullable = false, length = 500)
    private String questionContent;

    @Column(nullable = false, length = 1)
    private Boolean hasAnswer;

    @Column(nullable = false, length = 1)
    private Boolean isSecret;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

        public void update(PatchUpdateQuestionReq patchUpdateQuestionReq) {
        if (patchUpdateQuestionReq.getQuestionType() != null) {
            this.questionType = patchUpdateQuestionReq.getQuestionType();
        }
        if (patchUpdateQuestionReq.getQuestionTitle() != null) {
            this.questionTitle = patchUpdateQuestionReq.getQuestionTitle();
        }
        if (patchUpdateQuestionReq.getQuestionContent() != null) {
            this.questionContent = patchUpdateQuestionReq.getQuestionContent();
        }
        if (patchUpdateQuestionReq.getIsSecret() != null) {
            this.isSecret = patchUpdateQuestionReq.getIsSecret();
        }
    }

}
