package com.testehan.springsecurity.chpt11.authsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	So for this app, the AuthenticationServer app you have several Postman saved requests:
		1. chpt11-createUser	-> this will send a json representing the username and password that we want to create
		2. chpt11-authenticateUser -> this will authenticate the user, and if this works, a OTP, one time password will be
			persisted in the DB for the user that was authenticated
		3. chpt11-checkOneTimePassword -> you send the code that was saved in the DB at the previous step in order to
			verify if it is valid or not
*/


@SpringBootApplication
public class Chpt11AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt11AuthServerApplication.class, args);
	}

}
