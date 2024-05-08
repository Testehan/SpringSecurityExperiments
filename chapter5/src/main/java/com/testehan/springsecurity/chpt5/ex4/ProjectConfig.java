package com.testehan.springsecurity.chpt5.ex4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/home")     // use form login
            .and()
            .httpBasic();           // but also basic authentication
        http.authorizeRequests().anyRequest().authenticated();
        return http.build();
    }
}
