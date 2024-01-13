package com.example.lonua.product.repository.querydsl;

import com.example.lonua.brand.model.entity.QBrand;
import com.example.lonua.category.model.entity.QCategory;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductCount;
import com.example.lonua.product.model.entity.QProductImage;
import com.example.lonua.user.model.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl(){
        super(Product.class);
    }


    // 페이징 기능 사용을 위한 쿼리
    @Override
    public Page<Product> findList(Pageable pageable) {
        QProduct product = new QProduct("product");
        QProductImage productImage = new QProductImage("productImage");
        QProductCount productCount = new QProductCount("productCount");
        QBrand brand = new QBrand("brand");

        List<Product> result = from(product)
                .leftJoin(product.productImageList, productImage).fetchJoin()
                .leftJoin(product.productCount, productCount).fetchJoin()
                .leftJoin(product.brand, brand).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }
}