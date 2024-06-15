package com.testehan.springsecurity.chpt17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	curl -u nikolai:12345 http://localhost:8080/sell
		When we call the endpoint and authenticate with user Nikolai, we expect to see in the
		response only the two products associated with her

	curl -u julien:12345 http://localhost:8080/sell
		When we call the endpoint and we
		authenticate with Julien, we should only find in the response the one product associated with Julien

 */


@SpringBootApplication
public class Chpt17Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt17Application.class, args);
	}

}
