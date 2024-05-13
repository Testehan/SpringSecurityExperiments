package com.testehan.springsecurity.chpt10.ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ProjectConfig {

    @Autowired
    private CustomCsrfTokenRepository customCsrfTokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(customCsrfTokenRepository);
            c.ignoringRequestMatchers("/ciao");
            c.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"));});

        http.headers(headers -> headers.frameOptions().sameOrigin());       // needed for h2-console
        http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}
