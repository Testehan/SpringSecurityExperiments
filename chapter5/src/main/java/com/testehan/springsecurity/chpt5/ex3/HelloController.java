package com.testehan.springsecurity.chpt5.ex3;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    @GetMapping("/ciaoNPE")
    public String ciaoNullPointerIsThrown() throws Exception {
        SecurityContext contextOutside = SecurityContextHolder.getContext();
        Authentication auth = contextOutside.getAuthentication();
        System.out.println(auth.getName());

        Callable<String> task = () -> {
            /*
                If you run the application as is, you get nothing more than a NullPointerException.
                Inside the newly created thread to run the callable task, the authentication does not
                exist anymore, and the security context is empty
             */
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();
        try {
            return "Ciao, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        SecurityContext contextOutside = SecurityContextHolder.getContext();
        Authentication auth = contextOutside.getAuthentication();
        System.out.println(auth.getName());

        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();
        try {
            /*
                    Calling the endpoint now, you can observe that Spring propagated the security context to the
                    thread in which the tasks execute
             */
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }

}
