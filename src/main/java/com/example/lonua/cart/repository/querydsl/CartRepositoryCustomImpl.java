package com.example.lonua.cart.repository.querydsl;

import com.example.lonua.cart.model.entity.Cart;
import com.example.lonua.cart.model.entity.QCart;

import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.user.model.entity.QUser;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CartRepositoryCustomImpl extends QuerydslRepositorySupport implements CartRepositoryCustom {

    public CartRepositoryCustomImpl() {
        super(Cart.class);
    }


    @Override
    public Page<Cart> findList(Pageable pageable, Integer userIdx) {
        QCart cart = new QCart("cart");
        QProduct product = new QProduct("product");
        QUser user = new QUser("user");


        List<Cart> result = from(cart)
                .leftJoin(cart.product, product).fetchJoin()
                .leftJoin(cart.user, user).fetchJoin()
                .where(user.userIdx.eq(userIdx))
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



        return new PageImpl<>(result, pageable, result.size());
    }

}
