package com.testehan.springsecurity.chpt8.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                .password("12345")
                .roles("ADMIN")
                .build();
        var user2 = User.withUsername("jane")
                .password("12345")
                .roles("MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(httpSecurityHttpBasicConfigurer -> new HttpBasicConfigurer());
        http.csrf().disable();

        // Dan this was written with requirements from the book, but with the newer spring methods
        // i commented out this code so that i can test the part from below
//        http.authorizeHttpRequests(rmr -> rmr
//                .requestMatchers(antMatcher(HttpMethod.GET,"/a")).authenticated()
//                .requestMatchers(antMatcher(HttpMethod.POST,"/a")).permitAll()
//                .anyRequest().denyAll());

        http.authorizeHttpRequests(rmr -> rmr       // The regex refers to strings of any length,  containing any digit.
                // will deny curl http://localhost:8080/product/1234a
                // will accept curl http://localhost:8080/product/1234
                .requestMatchers("/product/{code:^[0-9]*$}").permitAll()
                .anyRequest().denyAll());

        return http.build();
    }
}
