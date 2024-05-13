package com.testehan.springsecurity.chpt10.ex5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // we focus on CORS in this example
        http.csrf().disable();
        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}
