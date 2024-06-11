package com.testehan.springsecurity.chpt11.businessrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	Before running this, run the Chpt11AuthServerApplication...also make sure that you have a user created,
	and for this see the notes from that class.

	Next
		curl -H "username:danielle" -H "password:12345" http://localhost:9090/login
			or
		in Postman you have "chpt11-loginUser to business srv STEP 1" request
	Once we call the /login endpoint, providing the correct username and password, we
	check the database for the generated OTP value. This should be a record in the otp
	table where the value of the username field is danielle. In my case, I have the following record:
	Username: danielle
	Code: 6271

	We assume this OTP was sent in an SMS message, and the user received it. We use it
	for the second authentication step. The cURL command in the next code snippet
	shows you how to call the /login endpoint for the second authentication step. I also
	add the -v option to see the response headers where I expect to find the JWT:
		curl -v -H "username:danielle" -H "code:6271" http:/./localhost:9090/login
			or
		in Postman you have "chpt11-loginUser to business srv STEP 2" request

	The JWT is right there where we expected it to be: in the authorization response
	header. Next, we use the token we obtained to call the /test endpoint:
		curl -H "Authorization:eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRhbmllbGxlIn0
		.wg6LFProg7s_KvFxvnYGiZF-Mj4rr-0nJA1tVGZNn8U" http://localhost:9090/test
			or
		in Postman you have "chpt11-getTestResource with JWT token" request

	The response body of this last request will be:
	Test
*/


@SpringBootApplication
public class Chpt11BusinessServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt11BusinessServerApplication.class, args);
	}

}
