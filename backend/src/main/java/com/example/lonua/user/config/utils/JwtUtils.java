package com.example.lonua.user.config.utils;

import com.example.lonua.seller.model.entity.Seller;
import com.example.lonua.user.exception.UserAccountException;
import com.example.lonua.user.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    // 일반 로그인 사용자 토큰 생성
    public static String generateAccessToken(User user, String secretKey, Long expiredTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("idx", user.getUserIdx());
        claims.put("email", user.getUserEmail());
        claims.put("name", user.getName());
        claims.put("grade", user.getGrade().getGradeType());
        claims.put("mileage", user.getUserMileage());
        claims.put("ROLE", user.getAuthority());

        byte[] secretBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 카카오 로그인 사용자 토큰 생성
    @Transactional
    public static String generateAccessTokenForOAuth(User user, String secretKey, Long expiredTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("idx", user.getUserIdx());
        claims.put("email", user.getUserEmail());
        claims.put("name", user.getName());
        claims.put("grade", user.getGrade().getGradeType());
        claims.put("mileage", user.getUserMileage());
        claims.put("ROLE", user.getAuthority());


        byte[] secretBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 브랜드 판매자 토큰 생성
    public static String generateAccessTokenForSeller(Seller seller, String secretKey, Long expiredTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("idx", seller.getSellerIdx());
        claims.put("email", seller.getSellerEmail());
        claims.put("ROLE", seller.getAuthority());

        byte[] secretBytes = secretKey.getBytes();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(Keys.hmacShaKeyFor(secretBytes), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // 키 변환 메서드
    public static Key getSignKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 사용자 이름 가져오는 메서드
    public static String getUserEmail(String token, String key) {
        return extractAllClaims(token, key).get("email", String.class);
    }

    public static String getAuthority(String token, String key) {
        return extractAllClaims(token, key).get("ROLE", String.class);
    }

    // 토근에서 정보를 가져오는 코드가 계속 중복되어 사용되기 때문에 별도의 메서드로 만들어서 사용하기 위한 것
    public static Claims extractAllClaims(String token, String key) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey(key))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw UserAccountException.forInvalidToken(token);
        } catch (ExpiredJwtException e) {
            throw UserAccountException.forExpiredToken(token);
        }
    }
}
