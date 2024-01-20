package com.example.lonua.Seller.repository;

import com.example.lonua.Seller.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    public Optional<Seller> findBySellerEmail(String email);

    public Optional<Seller> findBySellerIdx(Integer idx);

    public Integer deleteBySellerIdx(Integer idx);
}
