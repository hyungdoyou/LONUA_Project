package com.example.lonua.Seller.repository.querydsl;

import com.example.lonua.Seller.model.entity.Seller;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerRepositoryCustom {

    Page<Seller> findSellerList(Pageable pageable);
}
