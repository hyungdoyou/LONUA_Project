package com.example.lonua.product.repository.querydsl;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepositoryCustom {
    Page<Product> findList(Pageable pageable);

    Page<Product> findCategoryList(Pageable pageable, Integer idx);

    Page<Product> findStyleList(Pageable pageable, Integer idx);

    Page<Product> findSameTypeList(Pageable pageable, Integer upperType, Integer lowerType);

    Page<Product> findBrandList(Pageable pageable, Integer brandIdx);

    Optional<Product> findProduct(Integer idx);
}
