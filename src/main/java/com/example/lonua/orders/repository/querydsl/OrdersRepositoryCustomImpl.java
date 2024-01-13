package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.brand.model.entity.QBrand;
import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.QOrders;
import com.example.lonua.orders.model.entity.QOrdersProduct;
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
import java.util.Optional;
import java.util.stream.Collectors;

public class OrdersRepositoryCustomImpl extends QuerydslRepositorySupport implements OrdersRepositoryCustom {

    public OrdersRepositoryCustomImpl(){
        super(Orders.class);
    }


    // 페이징 기능 사용을 위한 쿼리
    @Override
    public Page<Orders> findList(Pageable pageable) {
        QOrders orders = new QOrders("orders");
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QUser user = new QUser("user");

        List<Orders> result = from(orders)
                .leftJoin(orders.ordersProductList, ordersProduct).fetchJoin()
                .leftJoin(orders.user, user).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Optional<Orders> findOrders(Integer idx) {
        QOrders orders = new QOrders("orders");
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");

        Optional<Orders> result = Optional.ofNullable(from(orders)
                .leftJoin(orders.ordersProductList, ordersProduct).fetchJoin()
                .where(orders.ordersIdx.eq(idx))
                .fetchOne()
        );

        return result;
    }
}
