package com.testehan.springsecurity.chpt16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	0. Make sure that for the project with Chpt14AuthorizationCodeGrantApplication,
	in the config bean, the bean with AuthorizationGrantType.CLIENT_CREDENTIALS is uncommented,
	as this allows this app to connect to the Authorization app in order to get the access token
	1. Run Chpt14AuthorizationCodeGrantApplication
	2. Run the Postman "chpt16-2ndEdition-OAuth2Client" to get the token...obviously in real life
	you would not use OAuth2AuthorizedClientManager directly in the controller. For a better design
	see "16-11 2nd book edition - very nice diagram of how ClientManager would be used in a production app .png"

 */

@SpringBootApplication
public class Chpt16ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt16ClientApplication.class, args);
	}

}
