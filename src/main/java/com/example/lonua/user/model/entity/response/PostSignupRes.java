package com.example.lonua.user.model.entity.response;

import com.example.lonua.common.BaseRes;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSignupRes{

    private String userEmail;

    private String userName;

}
