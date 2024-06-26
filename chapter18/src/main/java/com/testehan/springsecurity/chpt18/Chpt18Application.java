package com.testehan.springsecurity.chpt18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*

	Steps:
		1. make sure that the keycloack docker container is running

		2. get authorization token for bill
			curl -XPOST "http://localhost:8080/realms/master/protocol/openid-connect/token" -H "Content-Type: application/x-www-form-urlencoded" --data-urlencode "grant_type=password" --data-urlencode "username=bill" --data-urlencode "password=12345" --data-urlencode "scope=fitnessapp" --data-urlencode "client_id=fitnessapp"

 		3. we try to add a workout for bill using the access token
			curl -v -XPOST 'http://localhost:9090/workout/' -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJSR0luY1RZbDV4Z3VVbVYtUXlQTTJIaW56eXo2Y3J4eGxUV21mVF9nLUdFIn0.eyJleHAiOjE3MTY4MTIxMzEsImlhdCI6MTcxNjgwODUzMSwianRpIjoiOWI1OWFlZjYtMzQwYS00NGU5LTg0NjMtMGFmMDkwNTA5YzVmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9tYXN0ZXIiLCJhdWQiOiJmaXRuZXNzYXBwIiwic3ViIjoiMjQxZWQyNDMtODEyYy00NDc4LWE2MDUtNWFlMTgxOGU0NzUxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZml0bmVzc2FwcCIsInNlc3Npb25fc3RhdGUiOiJkZTNiYWQ5Yi0zZDU5LTRmOTUtOWM3NS1mMTlhN2ZkMjY1NzUiLCJzY29wZSI6ImZpdG5lc3NhcHAiLCJzaWQiOiJkZTNiYWQ5Yi0zZDU5LTRmOTUtOWM3NS1mMTlhN2ZkMjY1NzUiLCJ1c2VyX25hbWUiOiJiaWxsIiwiYXV0aG9yaXRpZXMiOlsiZml0bmVzc3VzZXIiXX0.Cc2eoZKymbv0iEn9-tLbtir3x-0nIWT5XfTrU3DSNOO-QHTob9NsRvqaqlvXMFAkqhLJ_06DDD6q7M_QVVLlytcc8GOBlPlhe89eYE7tM6nCHs2puuchTXAD4CqwZVXMx-Xe9hue4_6TMKJtB4QsGgyexujysBsvNNGfgAeNXbvoFZ0XzgW3TtmBHHyjPApmYvqAtPkwMO4HD2nPmxKNNj_uoeShwdD8pO1uKS1y22YZ8mMS-7ZQRcN_B8q8cXpbQzrgsDu5Kiv_0hg9fJQ-TxzbNGwVFp_X2IEbyocHrR29iAmJGa7Y-rCAbuPtiqxtgpoeWIs9MkvfIO0VvWe0jQ' -H 'Content-Type: application/json' --data-raw '{"user" : "bill", "start" : "2020-06-10T15:05:05", "end" : "2020-06-10T16:05:05", "difficulty" : 2 }'
				or
			postman "chpt18 - add workout for the logged in user"

		4. we try to add a workout for rachel using bills auth token
			postman "chpt18 - add workout for another user"
				=> 403

		5. GET WOrkout for a user
			postman "chpt18 - get workout logged in user"

		6. generate token for admin user Mary
			curl -XPOST "http://localhost:8080/realms/master/protocol/openid-connect/token" -H "Content-Type: application/x-www-form-urlencoded" --data-urlencode "grant_type=password" --data-urlencode "username=mary" --data-urlencode "password=12345" --data-urlencode "scope=fitnessapp" --data-urlencode "client_id=fitnessapp"

		7. delete TODO this does not work...
			problem i think is that from JWT, spring does not extract by default the authority, meaning the "fitnessadmin"
			role. If you debug you will see that the Authentication object, contains the authority "SCOPE_fitnessapp" only
			SecurityContextHolder.getContext().getAuthentication().getAuthorities()

			debug class JwtGrantedAuthoritiesConverter ...also check out link from below
			https://stackoverflow.com/questions/58146548/adopt-authorities-from-jwt-in-spring-resource-server

*/


@SpringBootApplication
public class Chpt18Application {

	public static void main(String[] args) {
		SpringApplication.run(Chpt18Application.class, args);
	}

}
