package com.example.lonua.seller.repository.querydsl;

import com.example.lonua.seller.model.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerRepositoryCustom {

    Page<Seller> findSellerList(Pageable pageable);
}
