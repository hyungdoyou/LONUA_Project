package com.example.lonua.product.repository;

import com.example.lonua.category.model.entity.Category;
import com.example.lonua.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByProductName(String productName);
}
