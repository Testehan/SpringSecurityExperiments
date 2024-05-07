package com.testehan.springsecurity.chpt5.ex2;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // one way of getting the authentication object
    @GetMapping("/hello")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        return "Hello, " + a.getName() + "!";
    }

    // injecting the authentication object
    @GetMapping("/hello2")
    public String hello2(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        // If you try the code as it is now, it throws a NullPointerException on the line that
        //  gets the name from the authentication, which is
        //  String username = context.getAuthentication().getName()
        // This is because the method executes now on another thread that does not inherit the security context
        String username = context.getAuthentication().getName();
        // do something with the username
    }
}
