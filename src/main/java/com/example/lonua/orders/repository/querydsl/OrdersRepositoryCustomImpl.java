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
    public Page<Orders> findList(Pageable pageable, Integer idx) {
        QOrders orders = new QOrders("orders");
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QUser user = new QUser("user");
        QProduct product = new QProduct("product");
        QProductCount productCount = new QProductCount("productCount");
        QProductImage productImage = new QProductImage("productImage");
        QBrand brand = new QBrand("brand");


        List<Orders> result = from(orders)
                .leftJoin(orders.ordersProductList, ordersProduct).fetchJoin()
                .leftJoin(orders.user, user).fetchJoin()
                .where(user.userIdx.eq(idx))
                .leftJoin(ordersProduct.product, product).fetchJoin()
                .leftJoin(product.productCount, productCount).fetchJoin()
                .leftJoin(product.brand, brand).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Optional<Orders> findOrders(Integer userIdx, Integer orderIdx, Integer productIdx) {
        QOrders orders = new QOrders("orders");
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QProduct product = new QProduct("product");
        QProductCount productCount = new QProductCount("productCount");
        QUser user = new QUser("user");
        QProductImage productImage = new QProductImage("productImage");

        Optional<Orders> result = Optional.ofNullable(from(orders)
                .leftJoin(orders.ordersProductList, ordersProduct).fetchJoin()
                .leftJoin(ordersProduct.product, product).fetchJoin()
                .leftJoin(product.productCount, productCount).fetchJoin()
                .leftJoin(orders.user, user).fetchJoin()
                .leftJoin(product.productImageList, productImage)
                .where(user.userIdx.eq(userIdx), orders.ordersIdx.eq(orderIdx), product.productIdx.eq(productIdx))
                .fetchOne()
        );

        // TODO : MultiBag 에러 해결을 통한 상품 이미지 테이블도 조인

        return result;
    }
}
