package com.example.lonua.style.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegisterStyleReq {

    @NotNull(message = "스타일 유형은 필수 입력 항목입니다.")
    @Length(max=15, message = "스타일 유형은 최대 15글자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣]*$", message = "스타일 유형은 한글만 입력해야 합니다.")
    @ApiModelProperty(value = "스타일 유형", example = "댄디", required = true)
    private String styleType;
}
