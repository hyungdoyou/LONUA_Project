package com.example.lonua.brand.repository;

import com.example.lonua.brand.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    public Optional<Brand> findByBrandName(String brandName);
    public Optional<Brand> findByBrandIdx(Integer idx);

    Page<Brand> findAll(Pageable pageable);

    Integer deleteByBrandIdx(Integer idx);
}
