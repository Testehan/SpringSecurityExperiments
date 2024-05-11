package com.testehan.springsecurity.chpt9.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {

    @Autowired
    private StaticKeyAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Observe that we don’t call the httpBasic() method from the HttpSecurity class
        // here because we don’t want the BasicAuthenticationFilter instance to be added
        // to the filter chain.
        return http.addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll().and().build();

    }
}
