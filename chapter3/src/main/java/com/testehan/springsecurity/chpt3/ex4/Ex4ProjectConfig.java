package com.testehan.springsecurity.chpt3.ex4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class Ex4ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        /*
            The JdbcUserDetailsManager also allows you to configure the queries used. In the
            previous example, we made sure we used the exact names for the tables and columns,
            as the JdbcUserDetailsManager implementation expects those. But it could be
            that for your application, these names are not the best choice. The next listing shows
            how to override the queries for the JdbcUserDetailsManager.

          String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
          String authsByUserQuery = "select username, authority from authorities where username = ?";
         */
//        jdbcUserDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

        return jdbcUserDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .and()
                .csrf().ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                .and()
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }
}
