package com.testehan.springsecurity.chpt10.ex1;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

public class CsrfTokenLogger implements Filter {
    private Logger logger = LoggerFactory.getLogger(CsrfTokenLogger.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;
        logger.info("CSRF token " + token.getToken());
        filterChain.doFilter(request, response);
    }
}
