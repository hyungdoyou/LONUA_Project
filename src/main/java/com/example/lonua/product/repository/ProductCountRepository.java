package com.example.lonua.product.repository;

import com.example.lonua.product.model.entity.ProductCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCountRepository extends JpaRepository<ProductCount, Integer> {

    public Integer deleteByProduct_ProductIdx(Integer idx);

    Optional<ProductCount> findByProduct_ProductIdx(Integer idx);

}
