package com.testehan.springsecurity.chpt9.ex1;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    The custom filter we add before authentication checks whether the Request-Id
    header exists. If the header exists on the request, the application forwards the request
    to be authenticated. If the header doesn’t exist, the application sets the HTTP status
    400 Bad Request and returns to the client.
*/

public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("Inside RequestValidationFilter processing the request");
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        String requestId = httpRequest.getHeader("Request-Id");
        if (requestId == null || requestId.isBlank()) {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
