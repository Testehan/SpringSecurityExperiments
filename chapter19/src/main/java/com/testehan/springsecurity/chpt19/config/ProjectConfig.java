package com.testehan.springsecurity.chpt19.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        var u = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        var userDetailsService = new MapReactiveUserDetailsService(u);
        return userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*
        you might notice that we add a bean of type SecurityWebFilterChain to
        the Spring context. The method receives as a parameter an object of type ServerHttpSecurity, which
        is injected by Spring. ServerHttpSecurity enables us to
        build an instance of SecurityWebFilterChain. ServerHttpSecurity provides
        methods for configuration similar to the ones you used when configuring authorization for non-reactive
        apps.
    */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeExchange(
                c -> c.pathMatchers(HttpMethod.GET, "/hello")
                        .authenticated()
                     .anyExchange()
                        .permitAll()
        );

        return http.build();
    }

}
