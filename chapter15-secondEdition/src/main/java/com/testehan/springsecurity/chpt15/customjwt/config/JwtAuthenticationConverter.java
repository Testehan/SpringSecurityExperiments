package com.testehan.springsecurity.chpt15.customjwt.config;

import org.springframework.core.convert.converter.Converter;
import com.testehan.springsecurity.chpt15.customjwt.model.CustomAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

/*
    Observe the
    two generic types we used: the Jwt and the CustomAuthentication. The first generic
    type, the Jwt, is the input for the converter, while the second type, the CustomAuthentication,
    is the output. So this converter changes a Jwt object (which is a
    standard contract in Spring Security on how the JWT access token is read) into the
    custom type we implemented, CustomAuthentication
*/
@Component
public class JwtAuthenticationConverter implements Converter<Jwt, CustomAuthentication> {
    @Override
    public CustomAuthentication convert(Jwt source) {
        List<GrantedAuthority> authorities = List.of(() -> "read");
        // Getting the priority values from the token’s custom claim
        String priority = String.valueOf(source.getClaims().get("priority"));
        return new CustomAuthentication(source, authorities, priority);
    }
}
