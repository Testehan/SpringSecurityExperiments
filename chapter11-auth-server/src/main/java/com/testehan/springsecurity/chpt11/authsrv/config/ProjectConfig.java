package com.testehan.springsecurity.chpt11.authsrv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ProjectConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.headers(headers -> headers.frameOptions().sameOrigin());       // needed for h2-console
        http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}
