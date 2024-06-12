package com.testehan.springsecurity.chpt14.clientcredentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1. Run the following :
		curl -X POST 'http://localhost:8080/oauth2/token?grant_type=client_credentials&scope=CUSTOM' --header 'Authorization: Basic Y2xpZW50OnNlY3JldA=='
				(nu merge cu curl pt ca aparent sunt prb cu query parameters...dar am facut in Postman un request
				"chpt14-2ndEdition-getAccessToken ClientCredentials" care merge)

	2. Result is something like this:
		{
			"access_token": "eyJraWQiOiIzYTM0ZTY2My03MmVmLTQyNDMtODM0Zi0xZmEwZjEyNDE5ZGQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjbGllbnQiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3MTU3Nzc1NzQsInNjb3BlIjpbIkNVU1RPTSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3MTU3Nzc4NzQsImlhdCI6MTcxNTc3NzU3NCwianRpIjoiMGExZjM1MmItNGNiZC00NjA0LWE4NTgtZjM0NjFmNzBkNTAyIn0.Xmc21fgDIh27BMcG0rKMc-r0WM4SoHHR1yA5LmrEQImaDhKBlZGt7POFAevHiwp4RxpHECZQzxNaVXqzp_KwY7cYhM2JFmT3IlkHKi0PlOboaiy_vaDTnH-jGIH9jnfTygMmDhnIGGpM_sxVvLYAgI4hkN4CfCd22K2_L7M0-57_M9eBbeBPxSnA-C61z1AfV05po2nfgrZdcLQOqqRcJSfpSWBgvOkhEHr7ugJwLkvtj_scydEcFv5xWKnDOuiGM7NpRSbIpZkvJy5fOvgqWC5lILtPHTCQiEBBRr2r5uOolf3wOYuTVmivK_uOBNkPSe1pxv6XNvBGn5bspOHCQQ",
			"scope": "CUSTOM",
			"token_type": "Bearer",
			"expires_in": 299
		}
 */

@SpringBootApplication
public class Chpt14ClientCredentialsGrantApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt14ClientCredentialsGrantApplication.class, args);
	}

}
