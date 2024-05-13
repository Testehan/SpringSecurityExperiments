package com.testehan.springsecurity.chpt10.ex4;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PostMapping("/hello")      // The /hello path remains under CSRF protection. You can’t call the endpoint without a valid CSRF token.
    public String postHello() {
        return "Post Hello!";
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello!";
    }
    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao";
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }
}
