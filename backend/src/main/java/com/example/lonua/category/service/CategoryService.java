package com.example.lonua.category.service;

import com.example.lonua.category.exception.CategoryDuplicateException;
import com.example.lonua.category.exception.CategoryNotFoundException;
import com.example.lonua.category.model.request.PatchUpdateCategoryReq;
import com.example.lonua.category.model.request.PostRegisterCategoryReq;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.category.repository.CategoryRepository;
import com.example.lonua.common.BaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Transactional(readOnly = false)
    public BaseRes register(PostRegisterCategoryReq postRegReqCategoryReq) {
        Optional<Category> result = categoryRepository.findByCategoryName(postRegReqCategoryReq.getCategoryName());

        if(result.isPresent()) {
            throw new CategoryDuplicateException(postRegReqCategoryReq.getCategoryName());
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

    @Transactional(readOnly = false)
    public BaseRes update(PatchUpdateCategoryReq patchUpdateCategoryReq) {
        Optional<Category> reuslt = categoryRepository.findByCategoryIdx(patchUpdateCategoryReq.getCategoryIdx());

        if(reuslt.isPresent()) {
            Category category = reuslt.get();
            category.update(patchUpdateCategoryReq);
            category.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            categoryRepository.save(category);

            return BaseRes.builder()
                    .code(200)
                    .isSuccess(true)
                    .message("카테고리 수정 성공")
                    .result(category.getCategoryName())
                    .build();
        } else {
            throw new CategoryNotFoundException(patchUpdateCategoryReq.getCategoryIdx());
        }
    }

    @Transactional(readOnly = false)
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
            throw new CategoryNotFoundException(idx);
        }
    }
}
