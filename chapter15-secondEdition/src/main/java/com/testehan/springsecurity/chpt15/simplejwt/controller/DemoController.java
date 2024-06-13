package com.testehan.springsecurity.chpt15.simplejwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {       // this represents the dummy resource that will be accessible only for authenticated
                                    // and authorized users
    @GetMapping("/demo")
    public String demo() {
        return "protected resource";
    }
}