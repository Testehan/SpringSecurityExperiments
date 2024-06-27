package com.testehan.springsecurity.chpt18.config;

import com.testehan.springsecurity.chpt18.model.CustomAuthentication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
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
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Getting the priority values from the token’s custom claim
        authorities.addAll ((Collection<? extends GrantedAuthority>) source.getClaims().get("authorities"));
        authorities.add(new SimpleGrantedAuthority("SCOPE_fitnessapp"));
        return new CustomAuthentication(source, authorities);
    }
}
