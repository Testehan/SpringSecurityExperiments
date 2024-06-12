package com.testehan.springsecurity.chpt14.opaquetokens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1. Run the following :
		curl -X POST 'http://localhost:8080/oauth2/token?grant_type=client_credentials&scope=CUSTOM' --header 'Authorization: Basic Y2xpZW50OnNlY3JldA=='
				(nu merge cu curl pt ca aparent sunt prb cu query parameters...dar am facut in Postman un request
				"chpt14-2ndEdition-getOpaqueToken" care merge)

	2. Result is something like this:	(token is much shorter..no longer a JWT token)
		{
		"access_token": "DyNffiRSWJWpV917mOtHV7rnRkTZLR37kYPo4Y8UIitHHp7M_IpvgrdzRtsC3UC6yVbzHS8Jch-hZl9jRIH6_Sg06C9Mst3NXEtRUpYylnLN9N8v4IevMDnC1F6rex-g",
		"scope": "CUSTOM",
		"token_type": "Bearer",
		"expires_in": 299
		}

	3. If you want to introspect the opaque token :
		curl -X POST 'http://localhost:8080/oauth2/introspect?token=DyNffiRSWJWpV917mOtHV7rnRkTZLR37kYPo4Y8UIitHHp7M_IpvgrdzRtsC3UC6yVbzHS8Jch-hZl9jRIH6_Sg06C9Mst3NXEtRUpYylnLN9N8v4IevMDnC1F6rex-g' --header 'Authorization: Basic Y2xpZW50OnNlY3JldA=='
 			(vezi request in postman "chpt14-2ndEdition-IntrospectOpaqueToken")

 */

@SpringBootApplication
public class Chpt14OpaqueTokensApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt14OpaqueTokensApplication.class, args);
	}

}
