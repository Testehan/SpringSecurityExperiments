package com.testehan.springsecurity.chpt3.ex3;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/*
    InMemoryUserDetailsManager. Because you are already familiar with how this
    implementation works, I have chosen a similar functionality, but this time to implement
    on our own
*/

public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> users;
    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
