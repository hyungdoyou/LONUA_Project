package com.example.lonua.style.repository;

import com.example.lonua.grade.model.entity.Grade;
import com.example.lonua.style.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StyleRepository extends JpaRepository<Style, Integer> {
    public Optional<Style> findByStyleType(String styleType);
    public Optional<Style> findByStyleIdx(Integer idx);

    public Integer deleteByStyleIdx(Integer idx);
}
