package com.testehan.springsecurity.chpt16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	Preauthorization examples:
	curl -u emma:12345 http://localhost:8080/hello
		Hello, Fantastico

	curl -u natalie:12345 http://localhost:8080/hello
		The response body is
		{
		 "status":403,
		 "error":"Forbidden",
		 "message":"Forbidden",
		 "path":"/hello"
		}

	curl -u emma:12345 http://localhost:8080/secret/names/emma
		["Fantastico"]

	curl -u emma:12345 http://localhost:8080/secret/names/natalie
		The response body is
		{
		 "status":403,
		 "error":"Forbidden",
		 "message":"Forbidden",
		 "path":"/secret/names/natalie"
		}

	Postauthorization examples:
	curl -u emma:12345 http://localhost:8080/book/details/emma
		{"name":"Emma Thompson","books":["Karamazov Brothers"],"roles":["accountant","reader"]}

	curl -u natalie:12345 http://localhost:8080/book/details/emma
		{"name":"Emma Thompson","books":["Karamazov Brothers"],"roles":["accountant","reader"]}

	curl -u emma:12345 http://localhost:8080/book/details/natalie
		{"timestamp":"2024-05-24T09:33:44.912+00:00","status":403,"error":"Forbidden","path":"/book/details/natalie"}

 	Permissions for methods:
 	curl -u natalie:12345 http://localhost:8080/documents/abc123
		{"owner":"natalie"}

 	curl -u natalie:12345 http://localhost:8080/documents/asd555
		(works because natalie is Admin)

	curl -u emma:12345 http://localhost:8080/documents/asd555
		{"owner":"emma"}

	curl -u emma:12345 http://localhost:8080/documents/abc123
		does not work because abc123 is not emmas document, and she is not admin

 */


@SpringBootApplication
public class Chpt16PreAndPostAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt16PreAndPostAuthorizationApplication.class, args);
	}

}
