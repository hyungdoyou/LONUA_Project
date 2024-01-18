package com.example.lonua.user.model.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEmailVerifyReq {

    @NotNull
    @Length(max=45)
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    @ApiModelProperty(value = "사용자 인증 이메일", example = "test@example.com", required = true)
    private String email;

    @NotNull
    @ApiModelProperty(value = "사용자 인증 토큰", example = "uuid", required = true)
    private String uuid;
}
