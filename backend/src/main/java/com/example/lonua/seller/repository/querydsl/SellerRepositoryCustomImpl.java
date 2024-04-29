package com.example.lonua.seller.repository.querydsl;

import com.example.lonua.seller.model.entity.QSeller;
import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.brand.model.entity.QBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class SellerRepositoryCustomImpl extends QuerydslRepositorySupport implements SellerRepositoryCustom{
    public SellerRepositoryCustomImpl() {
        super(Seller.class);
    }

    @Override
    public Page<Seller> findSellerList(Pageable pageable) {
        QSeller seller = new QSeller("seller");
        QBrand brand = new QBrand("brand");

        List<Seller> result = from(seller)
                .leftJoin(seller.brand, brand).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}
