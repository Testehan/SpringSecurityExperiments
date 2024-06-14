package com.testehan.springsecurity.chpt16.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    /*
        Similarly to other authentication methods, Spring Security offers a method of
        the HttpSecurity object to configure an app as an OAuth 2 client. Call the
        oauth2Client() method
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Client(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return http.build();
    }

    /*
        The app also needs to know some details to send the authorization server the requests
        for access tokens. As you learned in section 16.1, we provide these details using a
        ClientRegistrationRepository component.
        However, because I don’t use a common provider, I had to specify more details, such
        as the scope, the token URI, and the authentication method. Observe that I configured
        client credentials as a grant type.
    */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration c1 =
                ClientRegistration.withRegistrationId("1")
                        .clientId("client")
                        .clientSecret("secret")
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .tokenUri("http://localhost:8080/oauth2/token")
                        .scope(OidcScopes.OPENID)
                        .build();

        var repository = new InMemoryClientRegistrationRepository(c1);
        return repository;
    }

    /*
        The client manager is the Spring Security component responsible for connecting to the authorization server and
        properly using the grant type to get the access token.
    */
    @Bean
    public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
    ) {

        var provider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();
        var cm = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository,
                auth2AuthorizedClientRepository);

        cm.setAuthorizedClientProvider(provider);
        return cm;
    }
}
