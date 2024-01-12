package com.example.lonua.user.config.filter;

import com.example.lonua.user.config.utils.JwtUtils;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    private UserRepository userRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;

    public JwtFilter(String secretKey, UserRepository userRepository) {
        this.secretKey = secretKey;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String token;
        if (header!= null && header.startsWith("Bearer ")) {
            token = header.split(" ")[1];
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        String userEmail = JwtUtils.getUserEmail(token, secretKey);
        if(userEmail != null) {
            Optional<User> result = userRepository.findByUserEmail(userEmail);

            if(result.isPresent()) {
                User user = result.get();

                // 인가하는 코드
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                      user, null,
                      user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
        }
    }
}