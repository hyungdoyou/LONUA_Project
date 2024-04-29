package com.example.lonua.product.repository;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
    public Optional<Product> findByProductName(String productName);

    public Optional<Product> findByProductIdx(Integer productIdx);

    public Integer deleteByProductIdx(Integer idx);

    public List<Product> findAllByBrand_BrandIdx(Integer idx);
}
