package com.example.lonua.user.config;

import com.example.lonua.seller.repository.SellerRepository;
import com.example.lonua.user.config.filter.JwtFilter;
import com.example.lonua.user.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.lonua.user.exception.security.CustomAccessDeniedHandler;
import com.example.lonua.user.exception.security.CustomAuthenticationEntryPoint;
import com.example.lonua.user.repository.UserRepository;
import com.example.lonua.user.service.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final UserOAuth2Service userOAuth2Service;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // CORS 해결하기 위한 OPTION 메서드 허용
                    .antMatchers("/user/signup", "/seller/signup").permitAll()
                    .antMatchers("/user/verify").permitAll()
                    .antMatchers("/user/login", "/seller/login").permitAll()

                    .antMatchers("/branch/list", "/branch/read").hasAnyRole("USER", "SELLER")
                    .antMatchers("/branch/**").permitAll()

                    .antMatchers("/brand/register", "/brand/update", "/brand/delete/**").permitAll()
                    .antMatchers("/brand/list/**", "/brand/{idx}").permitAll()

                    .antMatchers("/cart/**").hasRole("USER")
                    .antMatchers("/coupon/list/**", "/coupon/apply/**").hasRole("USER")
                    .antMatchers("/likes/**").hasRole("USER")
                    .antMatchers("/orders/**").hasRole("USER")

                    .antMatchers("/product/sametype/**").permitAll()
                    .antMatchers("/product/register", "/product/update", "/product/delete/**").permitAll()
                    .antMatchers("/product/**").permitAll()

                    .antMatchers("/question/productQnA/**").permitAll()
                    .antMatchers("/question/list/**").hasAnyRole("SELLER", "USER")
                    .antMatchers("/question/**").hasRole("USER")

                    .antMatchers("/answer/**").hasRole("SELLER")

                    .antMatchers("/review/list/**", "/review/read/**").hasAnyRole("SELLER", "USER")
                    .antMatchers("/review/**").hasRole("USER")

                    .antMatchers("/user/read", "/user/update", "/user/cancle/**").hasRole("USER")

                    .antMatchers("/seller/update/**", "/seller/delete/**").hasRole("SELLER")

                    .antMatchers("/category/**").permitAll()
                    .antMatchers("/coupon/register", "/coupon/delete").hasRole("ADMIN")

                    .antMatchers("/grade/**").permitAll()
                    .antMatchers("/style/**").permitAll()
                    .antMatchers("/user/list/**", "/user/delete/**", "/seller/list/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler) // 인가에 대한 예외 처리
//                    .authenticationEntryPoint(customAuthenticationEntryPoint) // 인증에 대한 예외 처리
                    .and()
                    .formLogin().disable()
                    .addFilterBefore(new JwtFilter(secretKey, userRepository, sellerRepository), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    // OAuth 2.0 로그인 처리
                    .oauth2Login()
                    // 로그인 성공 시 실행할 커스텀 핸들러 설정
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    // 인증이 완료된 사용자에 대한 정보를 가져옴
                    .userInfoEndpoint()
                    // userInfoEndpoint로부터 얻은 사용자 정보를 처리할 사용자 서비스를 지정
                    .userService(userOAuth2Service);
            return  http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
