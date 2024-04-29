package com.example.lonua.orders.repository.querydsl;

import com.example.lonua.orders.model.entity.Orders;
import com.example.lonua.orders.model.entity.OrdersProduct;
import com.example.lonua.orders.model.entity.QOrders;
import com.example.lonua.orders.model.entity.QOrdersProduct;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductCount;
import com.example.lonua.product.model.entity.QProductImage;
import com.example.lonua.user.model.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class OrdersProductRepositoryCustomImpl extends QuerydslRepositorySupport implements OrdersProductRepositoryCustom {
    public OrdersProductRepositoryCustomImpl() {
        super(OrdersProduct.class);
    }

    @Override
    public Optional<OrdersProduct> findOrdersProduct(Integer userIdx, Integer ordersIdx, Integer productIdx) {
        QOrders orders = new QOrders("orders");
        QOrdersProduct ordersProduct = new QOrdersProduct("ordersProduct");
        QProduct product = new QProduct("product");
        QProductCount productCount = new QProductCount("productCount");
        QUser user = new QUser("user");
        QProductImage productImage = new QProductImage("productImage");

        Optional<OrdersProduct> result = Optional.ofNullable(from(ordersProduct)
                .leftJoin(ordersProduct.orders, orders).fetchJoin()
                .leftJoin(ordersProduct.product, product).fetchJoin()
                .leftJoin(product.productCount, productCount).fetchJoin()
                .leftJoin(orders.user, user).fetchJoin()
                .leftJoin(product.productImageList, productImage)
                .where(user.userIdx.eq(userIdx), orders.ordersIdx.eq(ordersIdx), product.productIdx.eq(productIdx))
                .fetchOne()
        );
        return result;
    }
}
