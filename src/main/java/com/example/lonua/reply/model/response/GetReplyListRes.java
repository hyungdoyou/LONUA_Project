package com.example.lonua.reply.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReplyListRes {
    private Integer replyIdx;
    private String replyContent;
    private String createdAt;
    private String updatedAt;
    private Integer brandIdx;
    private Integer questionIdx;
}
