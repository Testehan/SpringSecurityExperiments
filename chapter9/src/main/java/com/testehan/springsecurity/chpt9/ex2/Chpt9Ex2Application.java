package com.testehan.springsecurity.chpt9.ex2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
		Below will work because StaticKeyAuthenticationFilter looks for that header
		and compares the value from it with the value from application.properties
		curl -H "Authorization:SD9cICjl1e" localhost:8080/hello

*/


@SpringBootApplication
public class Chpt9Ex2Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt9Ex2Application.class, args);
	}

}
