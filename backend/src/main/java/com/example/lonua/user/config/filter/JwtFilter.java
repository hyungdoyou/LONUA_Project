package com.example.lonua.user.config.filter;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.seller.repository.SellerRepository;
import com.example.lonua.common.error.ErrorCode;
import com.example.lonua.common.error.ErrorResponse;
import com.example.lonua.user.config.utils.JwtUtils;
import com.example.lonua.user.exception.UserAccountException;
import com.example.lonua.user.model.entity.User;
import com.example.lonua.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private SellerRepository sellerRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;

    public JwtFilter(String secretKey, UserRepository userRepository, SellerRepository sellerRepository) {
        this.secretKey = secretKey;
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    // 필터에서 예외를 다루기 위한 처리
    private void handleJwtException(HttpServletResponse response, UserAccountException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        response.setContentType("application/json");

        // ErrorResponse 생성 및 반환
        ErrorCode errorCode;
        if (exception.getErrorCode() != null) {
            errorCode = exception.getErrorCode();
        } else {
            errorCode = ErrorCode.UNAUTHORIZED;
        }

        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), exception.getMessage());
        String jsonErrorResponse = new ObjectMapper().writeValueAsString(errorResponse);

        response.getWriter().write(jsonErrorResponse);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            String token;
            if (header != null && header.startsWith("Bearer ")) {
                token = header.split(" ")[1];
            } else {
                filterChain.doFilter(request, response);
                return;
            }

            String authority = JwtUtils.getAuthority(token, secretKey);

            if (authority.equals("ROLE_USER") || authority.equals("ROLE_ADMIN")) {
                String userEmail = JwtUtils.getUserEmail(token, secretKey);
                if (userEmail != null) {
                    Optional<User> result = userRepository.findByUserEmail(userEmail);

                    if (result.isPresent()) {
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
            } else if (authority.equals("ROLE_SELLER")) {
                String sellerEmail = JwtUtils.getUserEmail(token, secretKey);
                if (sellerEmail != null) {
                    Optional<Seller> result = sellerRepository.findBySellerEmail(sellerEmail);

                    if (result.isPresent()) {
                        Seller seller = result.get();

                        // 인가하는 코드
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                seller, null,
                                seller.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    }
                }
            }
        } catch (UserAccountException e) {
            // JwtUtils에서 던진 UserAccountException 처리
            handleJwtException(response, e);
        } catch (ServletException e) {
            // Spring Security 예외 처리
            handleJwtException(response, new UserAccountException(ErrorCode.UNAUTHORIZED, e.getMessage()));
        }

    }
}