package com.testehan.springsecurity.chpt13.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

import java.util.List;
import java.util.Map;

/*
     @EnableAuthorizationServer.. This way, we instruct Spring Boot to enable the
     configuration specific to the OAuth 2 authorization server
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    /*
       After exposing AuthenticationManager as a bean,
       we can now change the AuthServerConfig class to register the AuthenticationManager
       with the authorization server.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // OAuth2 Client info will be sored in memory
        var service = new InMemoryClientDetailsService();

        var clientDetails = new BaseClientDetails();    // an implementation of ClientDetails interface, similar to UserDetails but used for OAuth2 clients
        clientDetails.setClientId("client");
        clientDetails.setClientSecret("secret");
        clientDetails.setScope(List.of("read"));
        clientDetails.setAuthorizedGrantTypes(List.of("password"));

        service.setClientDetailsStore(Map.of("client", clientDetails));
        clients.withClientDetails(service);
    }

    // below is a shorter version of the above configuration
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("client")
//                .secret("secret")
//                .authorizedGrantTypes("password")
//                .scopes("read");
//    }
}