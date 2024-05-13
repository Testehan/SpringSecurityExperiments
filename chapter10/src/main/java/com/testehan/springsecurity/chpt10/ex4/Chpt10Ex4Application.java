package com.testehan.springsecurity.chpt10.ex4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
		curl -H "X-IDENTIFIER:12345" http://localhost:8080/hello
			this will return "Get Hello" but will not create a token in the DB ..(idk why in the book it created probably different version of spring security)
		curl -XPOST -H "X-IDENTIFIER:12345" -H "X-CSRF-TOKEN:bc26d206-2a75-496f-8efa-eba8587b61ae" http://localhost:8080/hello
			here is post with a dummy token, and this will cause a token to be generated and persisted in the DB
		next in order to get the actual TOKEN that i must use with the curl command, i added an endpoint to give me that
		information
			curl -H "X-IDENTIFIER:12345" http://localhost:8080/csrf
		Next if you cann with the correct token, obtained from the endpoint mentioned above, it will work
			curl -XPOST -H "X-IDENTIFIER:12345" -H "X-CSRF-TOKEN:F4_yGRfmUvAcyf-p5YEcTX1JchRxr0SujdnmaLebL-Aaw5osJ7nDKCWAZpQx8cqf1qwoLEx4X3YSnHaDvOHSCtWsSoV4-_8f" http://localhost:8080/hello

*/


@SpringBootApplication
public class Chpt10Ex4Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt10Ex4Application.class, args);
	}

}
