package com.example.lonua.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchUserUpdateReq {

    @NotNull
    @Length(max=45)
    @ApiModelProperty(value = "주소", example = "서울 동작구 보라매로 87 ", required = true)
    private String userAddr;

    @NotNull
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    @ApiModelProperty(value = "휴대전화 번호", example = "010-1234-5678", required = true)
    private String userPhoneNumber;

    @NotNull
    @Length(max=10)
    @ApiModelProperty(value = "선호 스타일", example = "캐주얼", required = true)
    private String preferStyle;

    @NotNull
    @Range(min = 1,max = 3)
    @ApiModelProperty(value = "상체 유형(1 ~ 3)", example = "1", required = true)    private Integer upperType;

    @NotNull
    @Range(min = 1,max = 3)
    @ApiModelProperty(value = "하체 유형(1 ~ 3)", example = "1", required = true)
    private Integer lowerType;
}
