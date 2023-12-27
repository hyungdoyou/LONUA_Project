package com.example.lonua.category.service;

import com.example.lonua.category.model.PostRegReq;
import com.example.lonua.category.model.entity.Category;
import com.example.lonua.category.repository.CategoryRepository;
import com.example.lonua.exception.ErrorCode;
import com.example.lonua.exception.exception.CategoryException;
import com.example.lonua.exception.exception.GradeException;
import com.example.lonua.grade.model.PostCreateReq;
import com.example.lonua.grade.model.entity.Grade;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void register(PostRegReq postRegReqReq) {
        Optional<Category> result = categoryRepository.findByCategoryName(postRegReqReq.getCategoryName());
        if(result.isPresent()) {
            throw new CategoryException(ErrorCode.DUPLICATED_USER, String.format("Category is %s", postRegReqReq.getCategoryName()));
        }

        categoryRepository.save(Category.builder()
                        .categoryName(postRegReqReq.getCategoryName())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .status(1)
                .build());
    }
}
