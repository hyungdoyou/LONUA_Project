package com.example.lonua.product.repository;

import com.example.lonua.product.model.entity.ProductIntrod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIntrodRepository extends JpaRepository<ProductIntrod, Integer> {
}
