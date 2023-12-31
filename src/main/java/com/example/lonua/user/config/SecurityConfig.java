package com.example.lonua.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

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
//                        .antMatchers("/login","/user/signup", "/grade/*", "/*").permitAll()
                        .antMatchers("/orders/register").hasRole("USER")
                        .anyRequest().permitAll()
                    .and()
                        .formLogin().loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/user/mypage");

            return  http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
