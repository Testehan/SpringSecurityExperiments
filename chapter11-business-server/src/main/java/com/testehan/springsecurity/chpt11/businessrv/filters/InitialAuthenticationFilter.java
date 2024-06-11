package com.testehan.springsecurity.chpt11.businessrv.filters;

import com.testehan.springsecurity.chpt11.businessrv.authentication.OtpAuthentication;
import com.testehan.springsecurity.chpt11.businessrv.authentication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;
    private final String signingKey;

    public InitialAuthenticationFilter(AuthenticationManager manager, String signingKey) {
        this.manager = manager;
        this.signingKey = signingKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");

        if (code == null) {
            Authentication a = new UsernamePasswordAuthentication(username, password);
            /*
                 We know (since chapter 2) that next, the AuthenticationManager tries to find a
                proper AuthenticationProvider. In our case, this is the UsernamePasswordAuthenticationProvider we wrote in listing 11.19. It’s the one triggered because
                its supports() method states that it accepts the UsernamePasswordAuthentication type.
             */
            manager.authenticate(a);
        } else {
            //  We consider, in this case, that the client sent an OTP for the second authentication step.
            Authentication a = new OtpAuthentication(username, code);
            manager.authenticate(a);

            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .setClaims(Map.of("username", username))
                    .signWith(key)
                    .compact();

            // Adds the token to the Authorization header of the HTTP response
            response.setHeader("Authorization", jwt);
        }

    }

    // we want to execute any request only on the /login path and skip all others.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
