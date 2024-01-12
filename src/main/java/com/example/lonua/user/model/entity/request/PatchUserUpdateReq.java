package com.example.lonua.user.model.entity.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUserUpdateReq {
    private String userAddr;
    private String userPhoneNumber;
    private String preferStyle;
    private Integer upperType;
    private Integer lowerType;
}
