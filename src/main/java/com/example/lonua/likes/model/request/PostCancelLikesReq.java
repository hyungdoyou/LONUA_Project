package com.example.lonua.likes.model.request;

import io.swagger.models.auth.In;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCancelLikesReq {

    private Integer likesIdx;
    private Integer productIdx;
}
