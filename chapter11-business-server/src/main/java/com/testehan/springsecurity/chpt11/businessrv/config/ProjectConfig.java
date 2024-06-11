package com.testehan.springsecurity.chpt11.businessrv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {

    // this bean will be used to call the Authentication Server implemented in chapter11-auth-server
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
