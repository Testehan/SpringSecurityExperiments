package com.testehan.springsecurity.chpt12.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${github.client.id}")
    private String githubClientId;

    @Value("${github.client.secret}")
    private String githubClientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(customizer -> customizer.clientRegistrationRepository(clientRepository()));

        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());

        return http.build();
    }

    // The authentication filter (OAuth2LoginAuthenticationFilter) obtains details about
    // the authorization server client registrations from a ClientRegistrationRepository.
    private ClientRegistrationRepository clientRepository() {
        var clientRegistration = githubClientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration githubClientRegistration() {
        ClientRegistration cr =
                CommonOAuth2Provider.GITHUB
                        .getBuilder("github")
                        .clientId(githubClientId)
                        .clientSecret(githubClientSecret)
                        .build();

        // this is what you would typically need to configure when using a custom authorization server,
        // however since Spring offers us the helper class CommonOAuth2Provider, that is used above to
        // simply the code. That class provides the OAuth2 Configuration for Google, Facebook, Okta, Github
//        ClientRegistration cr = ClientRegistration.withRegistrationId("github")
//                .clientId(githubClientId)
//                .clientSecret(githubClientSecret)
//                .scope("read:user")
//                .authorizationUri("https://github.com/login/oauth/authorize")
//                .tokenUri("https://github.com/login/oauth/access_token")
//                .userInfoUri("https://api.github.com/user")
//                .userNameAttributeName("id")
//                .clientName("GitHub")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
//                .build();

        return cr;
    }

}
