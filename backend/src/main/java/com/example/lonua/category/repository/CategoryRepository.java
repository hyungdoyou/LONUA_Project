package com.example.lonua.category.repository;

import com.example.lonua.category.model.entity.Category;
import com.example.lonua.grade.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Optional<Category> findByCategoryIdx(Integer idx);
    public Optional<Category> findByCategoryName(String categoryName);
    public Integer deleteByCategoryIdx(Integer idx);

}
