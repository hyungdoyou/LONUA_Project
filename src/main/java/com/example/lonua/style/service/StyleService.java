package com.example.lonua.style.service;

import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.StyleException;
import com.example.lonua.style.model.PostRegReq;
import com.example.lonua.style.model.entity.Style;
import com.example.lonua.style.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void register(PostRegReq postRegReq) {
        Optional<Style> result = styleRepository.findByStyleType(postRegReq.getStyleType());
        if(result.isPresent()) {
            throw new StyleException(ErrorCode.DUPLICATED_USER, String.format("Style is %s", postRegReq.getStyleType()));
        }

        styleRepository.save(Style.builder()
                .styleType(postRegReq.getStyleType())
                .build());
    }
}
