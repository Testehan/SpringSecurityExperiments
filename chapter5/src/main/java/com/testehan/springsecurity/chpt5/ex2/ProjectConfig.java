package com.testehan.springsecurity.chpt5.ex2;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync            // this annotation is needed to enable the @Asynch annotation from the controller
public class ProjectConfig {

    @Bean
    public InitializingBean initializingBean() {
        // see the notes to understand why this setting is needed. The short version is that SecurityContextHolder
        // is by default set on ThreadLocal mode. However, when you need to use Async methods, a new thread gets
        // created, and without this setting, the SecurityContext of that new thread will be empty. With this setting,
        // the SecurityContext of the new thread gets values from the initial SecurityContext object
        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
