package com.example.lonua.reply.model.entity;

import com.example.lonua.brand.model.entity.Brand;
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
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replyIdx;

    @OneToOne
    @JoinColumn(name = "Question_idx")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "Brand_idx")
    private Brand brand;

    @Column(nullable = false)
    private String replyContent;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

}
