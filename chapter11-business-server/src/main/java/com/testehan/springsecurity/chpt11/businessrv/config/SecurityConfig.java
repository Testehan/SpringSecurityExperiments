package com.testehan.springsecurity.chpt11.businessrv.config;

import com.testehan.springsecurity.chpt11.businessrv.filters.InitialAuthenticationFilter;
import com.testehan.springsecurity.chpt11.businessrv.filters.JwtAuthenticationFilter;
import com.testehan.springsecurity.chpt11.businessrv.providers.OtpAuthenticationProvider;
import com.testehan.springsecurity.chpt11.businessrv.providers.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
            Disable CSRF protection because, as you learned in chapter 10, this doesn’t
            apply when using different origins. Here, using a JWT replaces the validation
            that would be done with a CSRF token.
         */
        http.csrf().disable();

        var initialAuthFilter = new InitialAuthenticationFilter(authenticationManager(http),signingKey);
        http.addFilterAt(initialAuthFilter, BasicAuthenticationFilter.class)
            .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
//                .authenticationProvider(usernamePasswordAuthenticationProvider)
//                .authenticationProvider(otpAuthenticationProvider);

        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // retrieve builder from httpSecurity
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .authenticationProvider(usernamePasswordAuthenticationProvider)
                .authenticationProvider(otpAuthenticationProvider);
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
        http.authenticationManager(authenticationManager);

        return authenticationManager;
    }
}
