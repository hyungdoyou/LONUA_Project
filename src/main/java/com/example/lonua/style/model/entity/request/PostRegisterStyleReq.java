package com.example.lonua.style.model.entity.request;

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

    @NotNull
    @Length(max=7)
    @Pattern(regexp = "^[가-힣]*$")
    @ApiModelProperty(value = "스타일 유형", example = "댄디", required = true)
    private String styleType;
}
