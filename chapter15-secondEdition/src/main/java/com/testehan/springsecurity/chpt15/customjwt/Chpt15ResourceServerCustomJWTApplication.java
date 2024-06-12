package com.testehan.springsecurity.chpt15.customjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1. You first need to generate an access token. for that run Chpt14AuthorizationCodeGrantApplication and
	follow the steps there to obtain an access token

	2. Run the Postman "chpt15-2ndEdition-use access token to get a protected resource" or
		curl 'http://localhost:9090/demo' --header 'Authorization: Bearer eyJraW…'

 */

@SpringBootApplication
public class Chpt15ResourceServerCustomJWTApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt15ResourceServerCustomJWTApplication.class, args);
	}

}
