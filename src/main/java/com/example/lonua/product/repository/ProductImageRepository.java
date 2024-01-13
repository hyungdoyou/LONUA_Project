package com.example.lonua.product.repository;

import com.example.lonua.product.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    public Integer deleteAllByProduct_ProductIdx(Integer idx);
}
