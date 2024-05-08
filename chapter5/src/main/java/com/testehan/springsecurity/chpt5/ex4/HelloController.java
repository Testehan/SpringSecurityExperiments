package com.testehan.springsecurity.chpt5.ex4;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/formauth")
    public String formAuth() throws Exception {
        SecurityContext contextOutside = SecurityContextHolder.getContext();
        Authentication auth = contextOutside.getAuthentication();
        System.out.println(auth.getName());

       return auth.getName();
    }

}
