package com.example.lonua.product.repository;

import com.example.lonua.product.model.entity.ProductIntrodImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIntrodImageRepository extends JpaRepository<ProductIntrodImage, Integer> {

    public Integer deleteAllByProduct_ProductIdx(Integer idx);
}
