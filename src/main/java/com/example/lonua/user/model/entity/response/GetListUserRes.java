package com.example.lonua.user.model.entity.response;

import com.example.lonua.product.model.response.GetReadOrdersProductRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListUserRes {
    private Integer userIdx;
    private String userId;
    private String userName;
    private String userBirth;
    private String userGender;
    private String userPhoneNumber;
    private String userEmail;
    private String userAddr;
    private String preferStyle;
    private Integer upperType;
    private Integer lowerType;
    private String gradeType;

    private List<GetUserOrdersRes> getUserOrdersResList = new ArrayList<>();
}