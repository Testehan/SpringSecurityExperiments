package com.testehan.springsecurity.chpt15.customjwt.controller;

import com.testehan.springsecurity.chpt15.customjwt.model.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {       // this represents the dummy resource that will be accessible only for authenticated
                                    // and authorized users
    @GetMapping("/demo")
    public String demo(Authentication a) {
        return "protected resource with priority " + ((CustomAuthentication)a).getPriority();
    }
}