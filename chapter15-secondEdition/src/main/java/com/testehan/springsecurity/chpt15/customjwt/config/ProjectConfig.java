package com.testehan.springsecurity.chpt15.customjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Value("${keySetURI}")
    private String keySetUri;
    @Autowired
    private JwtAuthenticationConverter converter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuring the app as an OAuth 2 resource server
        http.oauth2ResourceServer(
                // Configuring the resource server to use JWTs for authentication
                customizer -> customizer.jwt(
                        // Configuring the public key set URL that the resource server will use to validate the tokens
                        jwtConfigurer -> jwtConfigurer.jwkSetUri(keySetUri)
                                // Configuring the converter object within the authentication mechanism
                                                        .jwtAuthenticationConverter(converter)
                )
        );

        http.authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated());

        return http.build();
    }
}
