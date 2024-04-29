package com.example.lonua.answer.model.entity;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.question.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Question_idx")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Seller_idx")
    private Seller seller;

    @Column(nullable = false, length = 500)
    private String answerContent;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

}
