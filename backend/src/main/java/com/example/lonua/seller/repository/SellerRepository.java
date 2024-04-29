package com.example.lonua.seller.repository;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.seller.repository.querydsl.SellerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>, SellerRepositoryCustom {
    public Optional<Seller> findBySellerEmail(String email);

    public Optional<Seller> findBySellerIdx(Integer idx);

    public Integer deleteBySellerIdx(Integer idx);
}
