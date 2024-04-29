package com.example.lonua.user.repository.querydsl;

import com.example.lonua.product.model.entity.Product;
import com.example.lonua.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepositoryCustom {

    Page<User> findUserList(Pageable pageable);

    Optional<User> findUser(String email);
}
