package com.testehan.springsecurity.chpt9.ex3;

import com.testehan.springsecurity.chpt9.ex1.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(httpSecurityHttpBasicConfigurer -> new HttpBasicConfigurer());
        http.addFilterAfter(
                new AuthenticationLoggingFilter(),
                BasicAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
}
