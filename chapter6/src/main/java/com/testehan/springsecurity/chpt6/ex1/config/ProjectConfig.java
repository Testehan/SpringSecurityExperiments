package com.testehan.springsecurity.chpt6.ex1.config;

import com.testehan.springsecurity.chpt6.ex1.security.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Autowired
    private AuthenticationProviderService authenticationProvider;

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public SCryptPasswordEncoder sCryptPasswordEncoder() {
//        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);
        http.formLogin()
                .defaultSuccessUrl("/main", true)
                .and()
                .httpBasic();

        http.authorizeRequests()
                // lines below are needed when you want access to the h2 when spring security is configured
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                .and()
//                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
//                .and()
//                .headers(headers -> headers.frameOptions().sameOrigin())
                .anyRequest()
                .authenticated()
                ;
        return http.build();
    }
}
