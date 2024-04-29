package com.example.lonua.product.model.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegisterProductIntrodReq {

    private Integer product_idx;
    private MultipartFile productIntrodImage;
}
