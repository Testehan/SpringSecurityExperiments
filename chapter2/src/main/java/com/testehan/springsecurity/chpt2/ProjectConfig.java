package com.testehan.springsecurity.chpt2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    // you could use this instead of configuring UserDetailsService and PasswordEncoder,
    // but see comment from class CustomAuthenticationProvider
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authenticationProvider(authenticationProvider); // this line only needed when using CustomAuthenticationProvider; if we commented out, it will use PasswordEncoder and UserDetailsService
        http.authorizeRequests().anyRequest()
//                .permitAll();                // use this when you don't want authentication
                .authenticated();             //use this when you want authentication
        return http.build();
    }

}