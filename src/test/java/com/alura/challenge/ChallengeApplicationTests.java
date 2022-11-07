package com.alura.challenge;




import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SuppressWarnings("deprecation")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ChallengeApplicationTests {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldPassIfStringMatches() throws Exception {
		assertNotEquals(restTemplate.getForObject("http://localhost:" + port + "/",String.class),"Hello World from Spring Boot");
	}

	@Test
	void contextLoads() {
	}
}
