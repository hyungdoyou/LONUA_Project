package com.example.lonua.cart.repository.querydsl;

import com.example.lonua.brand.model.entity.QBrand;
import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.model.entity.QCart;

import com.example.lonua.orders.model.entity.QOrders;
import com.example.lonua.orders.model.entity.QOrdersProduct;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductCount;
import com.example.lonua.user.model.entity.QUser;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class CartRepositoryCustomImpl extends QuerydslRepositorySupport implements CartRepositoryCustom {

    public CartRepositoryCustomImpl() {
        super(Cart.class);
    }


    @Override
    public Page<Cart> findList(Pageable pageable, Integer userIdx) {
        QCart cart = new QCart("cart");
        QProduct product = new QProduct("product");
        QProductCount productCount = new QProductCount("productCount");
        QUser user = new QUser("user");
        QBrand brand = new QBrand("brand");

        List<Cart> result = from(cart)
                .leftJoin(cart.product, product).fetchJoin()
                .leftJoin(cart.user, user).fetchJoin()
                .leftJoin(product.productCount, productCount).fetchJoin()
                .leftJoin(product.brand, brand).fetchJoin()
                .leftJoin(user.ordersList).fetchJoin()
                .where(user.userIdx.eq(userIdx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

}
