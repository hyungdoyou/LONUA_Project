package com.example.lonua.user.service;

import com.example.lonua.user.model.entity.EmailVerify;
import com.example.lonua.user.model.entity.request.GetEmailVerifyReq;
import com.example.lonua.user.repository.EmailVerifyRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private final EmailVerifyRepostiory emailVerifyRepository;

    @Transactional(readOnly = false)
    public void create(String email, String token) {
        emailVerifyRepository.save(EmailVerify.builder()
                .email(email)
                .uuid(token)
                .build());
    }

    public Boolean verify(GetEmailVerifyReq getEmailVerifyReq) {
        Optional<EmailVerify> result = emailVerifyRepository.findByEmail(getEmailVerifyReq.getEmail());
        if(result.isPresent()) {
            EmailVerify emailVerify = result.get();
            if(emailVerify.getUuid().equals(getEmailVerifyReq.getUuid())) {
                return true;
            }
        }
        return false;
    }
}
