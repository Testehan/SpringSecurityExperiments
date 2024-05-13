package com.testehan.springsecurity.chpt10.ex3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> { c.ignoringRequestMatchers("/ciao");});
        http.authorizeRequests().anyRequest().permitAll();

        return http.build();
    }
}
