package com.example.lonua.user.repository.querydsl;

import com.example.lonua.brand.model.entity.QBrand;
import com.example.lonua.grade.model.entity.QGrade;
import com.example.lonua.orders.model.entity.QOrders;
import com.example.lonua.product.model.entity.Product;
import com.example.lonua.product.model.entity.QProduct;
import com.example.lonua.product.model.entity.QProductCount;
import com.example.lonua.product.model.entity.QProductImage;
import com.example.lonua.user.model.entity.QUser;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryCustomImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryCustomImpl() {
        super(User.class);
    }

    @Override
    public Page<User> findUserList(Pageable pageable) {
        QUser user = new QUser("user");
        QOrders orders = new QOrders("orders");
        QGrade grade = new QGrade("grade");

        List<User> result = from(user)
                .leftJoin(user.ordersList, orders).fetchJoin()
                .leftJoin(user.grade, grade).fetchJoin()
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().stream().distinct().collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public Optional<User> findUser(String email) {
        QUser user = new QUser("user");
        QOrders orders = new QOrders("orders");
        QGrade grade = new QGrade("grade");

        Optional<User> result = Optional.ofNullable(from(user)
                .leftJoin(user.ordersList, orders).fetchJoin()
                .leftJoin(user.grade, grade).fetchJoin()
                .where(user.userEmail.eq(email))
                .fetchOne()
        );

        return result;
    }
}
