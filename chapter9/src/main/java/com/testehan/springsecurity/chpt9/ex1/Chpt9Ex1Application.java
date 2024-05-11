package com.testehan.springsecurity.chpt9.ex1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
		Below will work because RequestValidationFilter looks for that header
		curl -H "Request-Id:12345" http://localhost:8080/hello
		Below will not work
		curl -v http://localhost:8080/hello
*/


@SpringBootApplication
public class Chpt9Ex1Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt9Ex1Application.class, args);
	}

}
