package com.example.lonua.category.service;

import com.example.lonua.category.model.request.PostCategoryReq;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.category.repository.CategoryRepository;
import com.example.lonua.config.BaseRes;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.CategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public BaseRes register(PostCategoryReq postRegReqCategoryReq) {
        Optional<Category> result = categoryRepository.findByCategoryName(postRegReqCategoryReq.getCategoryName());
        if(result.isPresent()) {
            throw new CategoryException(ErrorCode.DUPLICATED_USER, String.format("Category is %s", postRegReqCategoryReq.getCategoryName()));
        }

        categoryRepository.save(Category.builder()
                        .categoryName(postRegReqCategoryReq.getCategoryName())
                        .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                        .updatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                        .status(true)
                .build());

        return BaseRes.builder()
                .code(200)
                .isSuccess(true)
                .message("카테고리 등록 성공")
                .result(postRegReqCategoryReq.getCategoryName())
                .build();
    }

    public BaseRes update(PostCategoryReq postCategoryReq) {
        Optional<Category> reuslt = categoryRepository.findByCategoryName(postCategoryReq.getCategoryName());

        if(reuslt.isPresent()) {
            Category category = reuslt.get();
            category.update(postCategoryReq);
            category.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            categoryRepository.save(category);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("카테고리 수정 성공")
                    .result(category.getCategoryName())
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("카테고리 수정 실패")
                    .result("잘못된 요청입니다.")
                    .build();
        }
    }

    public BaseRes delete(Integer idx) {
        Integer result = categoryRepository.deleteByCategoryIdx(idx);

        if(!result.equals(0)) {
            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("카테고리 삭제 성공")
                    .result("정상 처리 되었습니다.")
                    .build();
        } else {
            return BaseRes.builder()
                    .code(400)
                    .isSuccess(false)
                    .message("카테고리 삭제 실패")
                    .result("잘못된 요청입니다.")
                    .build();
        }
    }
}
