package com.testehan.springsecurity.chpt10.ex5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	If you start this app, with no CORS settings, if you go in the browser to http://localhost:8080/
	you will see nothing displayed. (You should have seen "HELLO"). If you open Chrome Dev Tools you
	will see in the network tab a CORS error

*/

@SpringBootApplication
public class Chpt10Ex5Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt10Ex5Application.class, args);
	}

}
