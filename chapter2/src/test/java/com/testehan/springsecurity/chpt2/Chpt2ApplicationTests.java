package com.testehan.springsecurity.chpt2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest		//  Makes Spring Boot responsible for managing the Spring context for the tests
@AutoConfigureMockMvc
public class Chpt2ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void helloUnauthenticated() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(status().isUnauthorized());
//				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void helloAuthenticated() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(content().string("Hello!"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "mary")		// in case you need to have a username for the mock user
	public void helloAuthenticatedWithName() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(content().string("Hello!"))
				.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("john")	// testing with a real user, defined in our UserDetailsService
	public void helloAuthenticatedWithRealUser() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(status().isOk());
	}

}
