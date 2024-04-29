package com.example.lonua.user.repository;

import com.example.lonua.user.model.entity.EmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyRepostiory extends JpaRepository<EmailVerify, Integer> {
    public Optional<EmailVerify> findByEmail(String email);
}
