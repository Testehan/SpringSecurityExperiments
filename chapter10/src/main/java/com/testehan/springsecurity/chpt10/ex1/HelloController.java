package com.testehan.springsecurity.chpt10.ex1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello!";
    }
    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }
}
