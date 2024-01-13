package com.example.lonua.product.repository.querydsl;

import com.example.lonua.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryCustom {
    Page<Product> findList(Pageable pageable);
}
