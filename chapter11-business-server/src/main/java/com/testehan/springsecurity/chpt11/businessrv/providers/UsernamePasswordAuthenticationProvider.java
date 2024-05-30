package com.testehan.springsecurity.chpt11.businessrv.providers;

import com.testehan.springsecurity.chpt11.businessrv.authentication.UsernamePasswordAuthentication;
import com.testehan.springsecurity.chpt11.businessrv.proxy.AuthenticationServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        /*
            Uses the proxy to call the authentication server. It sends the OTP to the
            client through SMS...well in our case it just persists OTP in the DB :)
         */

        proxy.sendAuth(username, password);
        // Remember, we discussed in section 11.4.1 that the constructor with two parameters doesn’t
        // mark the object as being authenticated ( this constructor sets this.setAuthenticated(false);).
        // the constructor with 3 params marks the object as being authenticated
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(aClass);
    }
}
