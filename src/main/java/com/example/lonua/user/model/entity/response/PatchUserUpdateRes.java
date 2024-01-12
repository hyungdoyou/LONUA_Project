package com.example.lonua.user.model.entity.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUserUpdateRes {
    private String userAddr;
    private String userPhoneNumber;
    private String preferStyle;
    private Integer upperType;
    private Integer lowerType;
}
