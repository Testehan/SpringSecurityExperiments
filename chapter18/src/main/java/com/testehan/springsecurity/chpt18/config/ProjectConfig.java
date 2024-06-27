package com.testehan.springsecurity.chpt18.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class ProjectConfig{

    @Value("${claim.aud}")
    private String claimAud;
    @Value("${jwkSetUri}")
    private String urlJwk;

    @Autowired
    private JwtAuthenticationConverter converter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"));});

        http.headers(headers -> headers.frameOptions().sameOrigin());       // needed for h2-console
        http.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();

        // Configuring the app as an OAuth 2 resource server
        http.oauth2ResourceServer(
                // Configuring the resource server to use JWTs for authentication
                customizer -> customizer.jwt(
                        // Configuring the public key set URL that the resource server will use to validate the tokens
                        jwtConfigurer -> jwtConfigurer.jwkSetUri(urlJwk)
                                // Configuring the converter object within the authentication mechanism
//                                .jwtAuthenticationConverter(converter)
                )
        );

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, "/**")
                .hasAuthority("fitnessadmin")
                .anyRequest().authenticated();

        return http.build();
    }

    /*
        SecurityEvaluationContextExtension. Our application needs this
        extension to evaluate the SpEL expression we used at the repository layer (see findAllByUser
        from WorkoutRepository )
    */
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
