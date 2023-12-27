package com.example.lonua.category.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRegReq {
    private String categoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;
    private Integer status;

}
