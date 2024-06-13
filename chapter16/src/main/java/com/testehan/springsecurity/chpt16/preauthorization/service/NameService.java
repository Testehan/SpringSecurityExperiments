package com.testehan.springsecurity.chpt16.preauthorization.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private Map<String, List<String>> secretNames =
            Map.of(
                    "natalie", List.of("Energico", "Perfecto"),
                    "emma", List.of("Fantastico"));


    // Defines the authorization rule. Only users having write authority can call the method.
    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "Fantastico";
    }

    /*
        we use #name to refer to the value of the getSecretNames() method parameter called name, and we have access directly to the
        authentication object that we can use to refer to the currently authenticated user. The
        expression we use indicates that the method can be called only if the authenticated
        user’s username is the same as the value sent through the method’s parameter.
    */
    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }

}
