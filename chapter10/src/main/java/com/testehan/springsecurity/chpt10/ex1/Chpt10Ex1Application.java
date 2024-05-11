package com.testehan.springsecurity.chpt10.ex1;

/*
		Below if you run
			curl -v http://localhost:8080/hello
		in the cmd line response you will see a sessionId...also..
		you will see in the server logs something like below, because the filter CsrfTokenLogger prints out CSRF
		"CSRF token iAuKcP1wYGfKtx2s2UtZ7-_3vDXUzT9z8yq5Dk5AcF70dcb0uG_vEs1AUlTn1S6f6WZtit6UkVe19AdekBKKNyt3EjyWFqfB"

		Next if you call
			curl -XPOST http://localhost:8080/hello
		you will get bag 403 forbidden..
		But if you provide the correct value for the CSRF token, the call is successful. You also
		need to specify the session ID (JSESSIONID) because the default implementation of
		CsrfTokenRepository stores the value of the CSRF token on the session
			curl -X POST http://localhost:8080/hello
			-H 'Cookie: JSESSIONID=F586809F403A2D021333ADAD57789D8A'
			-H 'X-CSRF-TOKEN: kjY_EV2YR4aT-7YZ21DqZKzAkmJEAAZEN2jgiZchGssNo7Bpq1RaczyrceS-zIF7un3eBs-mvwN8NWJpAVGCu6dDKK08koFd'
*/


//@SpringBootApplication
public class Chpt10Ex1Application {

//	public static void main(String[] args) {
//		SpringApplication.run(Chpt10Ex1Application.class, args);
//	}

}
