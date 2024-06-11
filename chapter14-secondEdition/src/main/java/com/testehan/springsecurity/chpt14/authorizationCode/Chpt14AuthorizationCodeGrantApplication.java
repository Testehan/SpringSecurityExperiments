package com.testehan.springsecurity.chpt14.authorizationCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1. Run this
	2. Go to http://localhost:8080/.well-known/openid-configuration and see some of the below relevant information
		- "authorization_endpoint": "http://localhost:8080/oauth2/authorize"
			The authorization endpoint to which a client will redirect the user to authenticate

 		- "token_endpoint": "http://localhost:8080/oauth2/token",
 			The token endpoint the client will call to request an access token

 		- "jwks_uri": "http://localhost:8080/oauth2/jwks"
 			The key-set endpoint a resource server will call to get the public keys it can use
			to validate tokens

		- "introspection_endpoint": "http://localhost:8080/oauth2/introspect",
			The introspection endpoint a resource server can call to validate opaque tokens

	3. In order to generate the code_challenge and verifier use class GenerateVerifierAndChallenge
	3. From browser send the following request:
		http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://www.manning.com/authorized&code_challenge=HkwdhNhqWhyfmYGz4Vvoqn6XrlEwSGZ6s8uOhyJDAAY&code_challenge_method=S256
	4. You will see the Spring form login...enter user and pass configured in SecurityConfig class
	5. After successfully login you will be redirected to the configured redirect URL
		https://www.manning.com/authorized?code=ZHuptBfkzdS4HFWtebj9j8VInbJRzosmQV1znVw8muiMo9Hb0bZV_lHJit98VRviAopGHZLnrRhftprEUhsPYQyjz857PUsswvOO0vLHEsXsjP_87nyv1Xl0y24EuN-m

		Following successful authentication, the authorization server guides the user to the
		specified redirect URI and issues an authorization code. The client then utilizes this code to acquire an
		access token.

	6. Once the client has the authorization code, it can request an access token. The client
		can request the access token using the token endpoint
		curl -X POST 'http://localhost:8080/oauth2/token?client_id=client&redirect_uri=https://www.manning.com/authorized&grant_type=authorization_code&code=9ouyCkq4gTMOH_mDY__nJu4jKV2leB1zvNve0DaKD55G1Qru0I2CN7xkXezpHzjoRqvRAg13WQOhGrsQPj8UyLOuzzoIR7YKYCUt1px7edCkbolJSRY4CUgIb1_UBE0j&code_verifier=48YudZ3JmfieqGQGFCQLJTYLzrXeotBCasf0yixbqPA' --header 'Authorization: Basic Y2xpZW50OnNlY3JldA=='
			(nu merge cu curl pt ca aparent sunt prb cu query parameters...dar am facut in Postman un request chpt14-2ndEdition-getAccessToken
			care merge)

	7. Result of call from step 6 is something like...from which you can use the access token to access resources:
		{
			"access_token": "eyJraWQiOiI1ZmM0NmRlYS00NjI4LTRhMjItYTYxMS1hNGJlZjA0NDcxY2QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiaWxsIiwiYXVkIjoiY2xpZW50IiwibmJmIjoxNzE1Nzc2NjEzLCJzY29wZSI6WyJvcGVuaWQiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZXhwIjoxNzE1Nzc2OTEzLCJpYXQiOjE3MTU3NzY2MTMsImp0aSI6ImZjZWRmYjRjLTBmY2UtNGJmYi05YTQ3LTcwM2E1YTliNjgyYiJ9.qd4k5o9KU3OTFGyTAQDyHeGaPD_Y9svq9YEkjy0wQb99BFuGrWcsCaC_SzXTEX1S45FpY5PYYOd_x5_bQ2vUPXC-H2ZQdaP7_yMQ0MdLWh1fH4zOisWnThp42iN33DDovM8ucjnan25uL_g6I8se6mU6zNVdvF0G-jOmhgplY1PhG6CKZsWiRoV9TR5i7iOauq_kE5zGnu2qXwltk79f3pBv9hg79JTQq-08esh_H4L3AcEc6sYfWZRTvskM1-VW4M8mUGbSIZeiP8q9oQAR7BqMgB1D3l50n_XeY9EGrinFWGWJTye9Axi5T2bEddODt_Mmt2wFfyQOEq7-FOKRLQ",
			"scope": "openid",
			"id_token": "eyJraWQiOiI1ZmM0NmRlYS00NjI4LTRhMjItYTYxMS1hNGJlZjA0NDcxY2QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJiaWxsIiwiYXVkIjoiY2xpZW50IiwiYXpwIjoiY2xpZW50IiwiYXV0aF90aW1lIjoxNzE1Nzc2NTcyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3MTU3Nzg0MTMsImlhdCI6MTcxNTc3NjYxMywianRpIjoiYWRmN2EwY2YtZjkzOS00MWJjLTk4OWItZDg0NTgwMmEzMDgxIiwic2lkIjoiVTNiSHRZYlQ0dmotNnRVN052Ym1TMkY5MXlOOFdVbUpaNkRrZENnYVdsOCJ9.lydMDytHVJnWHASWnNxtEkiL74xzxmYuQBaV4VQs2baTlnCufwoC2jpWNDWNNu1aSJxQdkKmwO91ukoral9TDKiibV5LG-eZxHgAbObrPZE0WHO8UrmL5xRxo74W69aY66eNsyq5cyoe4OTAXCvt4pEhNm9oU9GqajWHYRNhXdJh0jSGVv-Hs1AMRiIQzlMa-z3J_Cz2v2FWGF1kgWjC5LSshpoyYRcpHQNahbFkd-vJpxcTABp8pfhNEjYG0tYEH6Pgj0DK6OJxBheC36k0bty1GZo8ExfKEGXMTDRRZoHI6UvWyTIgd8dVgesqRr_FgAdbIR_l9pdODzQapoqLJA",
			"token_type": "Bearer",
			"expires_in": 299
		}


 */

@SpringBootApplication
public class Chpt14AuthorizationCodeGrantApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt14AuthorizationCodeGrantApplication.class, args);
	}

}
