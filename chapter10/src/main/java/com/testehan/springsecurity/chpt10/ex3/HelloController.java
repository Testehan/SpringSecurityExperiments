package com.testehan.springsecurity.chpt10.ex3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PostMapping("/hello")      // The /hello path remains under CSRF protection. You can’t call the endpoint without a valid CSRF token.
    public String postHello() {
        return "Post Hello!";
    }
    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao";
    }
}
