package com.testehan.springsecurity.chpt9.ex3;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
    To make it crystal clear how to use such a class, let’s write an example. The logging
    functionality we implemented in section 9.3 makes a great candidate for using
    OncePerRequestFilter. We want to avoid logging the same requests multiple times.
    Spring Security doesn’t guarantee the filter won’t be called more than once, so we have
    to take care of this ourselves. The easiest way is to implement the filter using the
    oncePerRequestFilter class
*/

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger =  LoggerFactory.getLogger(AuthenticationLoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        logger.info("Successfully authenticated request with id " + requestId);
        filterChain.doFilter(request, response);
    }
}
