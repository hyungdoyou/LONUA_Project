package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.OrdersProduct;
import com.example.lonua.orders.model.entity.QOrders;
import com.example.lonua.orders.model.entity.QOrdersProduct;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductImage;
import com.example.lonua.user.model.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrdersProductRepositoryCustomImpl extends QuerydslRepositorySupport implements OrdersProductRepositoryCustom {

    public OrdersProductRepositoryCustomImpl(){
        super(OrdersProduct.class);
    }

    @Override
    public Page<OrdersProduct> findList(Pageable pageable) {
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QOrders orders  = new QOrders("orders");
        QProduct product = new QProduct("product");
        QUser user = new QUser("user");

        List<OrdersProduct> result = from(ordersProduct)
                .leftJoin(ordersProduct.orders, orders).fetchJoin()
                .leftJoin(ordersProduct.product, product).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Optional<OrdersProduct> findOrdersProduct(Integer idx) {
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QOrders orders  = new QOrders("orders");
        QProduct product = new QProduct("product");

        Optional<OrdersProduct> result = Optional.ofNullable(from(ordersProduct)
                .leftJoin(ordersProduct.orders, orders).fetchJoin()
                .leftJoin(ordersProduct.product, product).fetchJoin()
                .where(ordersProduct.ordersProductIdx.eq(idx))
                .fetchOne()
        );

        return result;
    }
}
