package com.example.lonua.user.config;

import com.example.lonua.user.config.filter.JwtFilter;
import com.example.lonua.user.config.handler.OAuth2AuthenticationSuccessHandler;
import com.example.lonua.user.exception.CustomAccessDeniedHandler;
import com.example.lonua.user.exception.CustomAuthenticationEntryPoint;
import com.example.lonua.user.repository.UserRepository;
import com.example.lonua.user.service.UserOAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;
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
                    .antMatchers("/user/signup/**").permitAll()
                    .antMatchers("/user/verify/**").permitAll()
                    .antMatchers("/user/login/**").permitAll()
                    .antMatchers("/branch/**").hasRole("USER")
                    .antMatchers("/brand/**").hasRole("USER")
                    .antMatchers("/cart/**").hasRole("USER")
                    .antMatchers("/coupon/list/**").hasRole("USER")
                    .antMatchers("/likes/**").hasRole("USER")
                    .antMatchers("/orders/**").hasRole("USER")
                    .antMatchers("/product/**").hasRole("USER")
                    .antMatchers("/question/**").hasRole("USER")
                    .antMatchers("/reply/**").hasRole("USER")
                    .antMatchers("/review/**").hasRole("USER")
                    .antMatchers("/user/read/**").hasRole("USER")
                    .antMatchers("/user/update/**").hasRole("USER")
                    .antMatchers("/user/cancle/**").hasRole("USER")
                    .antMatchers("/category/**").hasRole("ADMIN")
                    .antMatchers("/coupon/register/**").hasRole("ADMIN")
                    .antMatchers("/coupon/delete/**").hasRole("ADMIN")
                    .antMatchers("/grade/**").hasRole("ADMIN")
                    .antMatchers("/style/**").hasRole("ADMIN")
                    .antMatchers("/user/list/**").hasRole("ADMIN")
                    .antMatchers("/user/delete/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler) // 인가에 대한 예외 처리
                    .authenticationEntryPoint(customAuthenticationEntryPoint) // 인증에 대한 예외 처리
                    .and()
                    .formLogin().disable()
                    .addFilterBefore(new JwtFilter(secretKey, userRepository), UsernamePasswordAuthenticationFilter.class)
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
