package com.example.lonua.user.repository;

import com.example.lonua.brand.model.entity.Brand;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.repository.querydsl.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    public Optional<User> findByUserIdx(Integer userIdx);

    public Integer deleteByUserIdx(Integer idx);
    public Optional<User> findByUserEmail(String email);
    public Optional<User> findByUserPhoneNumber(String phoneNumber);
}
