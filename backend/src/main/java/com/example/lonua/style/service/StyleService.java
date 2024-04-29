package com.example.lonua.style.service;

import com.example.lonua.common.BaseRes;
import com.example.lonua.style.exception.StyleDuplicateException;
import com.example.lonua.style.exception.StyleNotFoundException;
import com.example.lonua.style.model.request.PatchUpdateStyleReq;
import com.example.lonua.style.model.request.PostRegisterStyleReq;
import com.example.lonua.style.model.entity.Style;
import com.example.lonua.style.model.response.GetListStyleRes;
import com.example.lonua.style.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StyleService {
    private final StyleRepository styleRepository;

    @Transactional(readOnly = false)
    public BaseRes register(PostRegisterStyleReq postRegisterStyleReq) {
        Optional<Style> result = styleRepository.findByStyleType(postRegisterStyleReq.getStyleType());

        // 스타일 유형 중복 예외 처리
        if(result.isPresent()) {
            throw new StyleDuplicateException(postRegisterStyleReq.getStyleType());
        }

        styleRepository.save(Style.builder()
                .styleType(postRegisterStyleReq.getStyleType())
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("스타일 등록 성공")
                .result(postRegisterStyleReq.getStyleType())
                .build();
    }

    @Transactional(readOnly = true)
    public BaseRes list() {
        List<Style> styleList = styleRepository.findAll();

        if(styleList != null) {
            List<GetListStyleRes> getListStyleResList = new ArrayList<>();
            for(Style style : styleList) {
                GetListStyleRes getListStyleRes = GetListStyleRes.builder()
                        .StyleType(style.getStyleType())
                        .build();

                getListStyleResList.add(getListStyleRes);
            }

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("요청 성공")
                    .result(getListStyleResList)
                    .build();
        } else {
            throw new StyleNotFoundException(null);
        }
    }

    @Transactional(readOnly = false)
    public BaseRes update(PatchUpdateStyleReq patchUpdateStyleReq) {
        Optional<Style> result = styleRepository.findByStyleIdx(patchUpdateStyleReq.getStyleIdx());

        if(result.isPresent()) {
            Style style = result.get();
            style.setStyleType(patchUpdateStyleReq.getStyleType());

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("수정 성공")
                    .result(patchUpdateStyleReq.getStyleType())
                    .build();
        } else {
            throw new StyleNotFoundException(patchUpdateStyleReq.getStyleIdx());
        }
    }

    @Transactional(readOnly = false)
    public BaseRes delete(Integer idx) {
        Integer result = styleRepository.deleteByStyleIdx(idx);

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("삭제 성공")
                    .result("요청 성공")
                    .build();
        } else {
            throw new StyleNotFoundException(idx);
        }
    }
}
