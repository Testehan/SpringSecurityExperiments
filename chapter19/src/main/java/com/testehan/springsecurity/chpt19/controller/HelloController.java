package com.testehan.springsecurity.chpt19.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello(Mono<Authentication> auth) {  // Requests the framework to provide the authentication object

        // Creates and returns a Mono stream source with one value on the stream
//        return Mono.just("Hello!");

        Mono<String> message = auth.map(a -> "Hello " + a.getName());
        return  message;
    }

    @GetMapping("/ciao")
    public Mono<String> ciao() {
        return Mono.just("Ciao!");
    }
}