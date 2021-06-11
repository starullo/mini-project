package com.sam.mini.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.mini.domain.Movie;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		String m1AsJson = this.mapper.writeValueAsString(m1);
		
		RequestBuilder mockRequest = 
				post("/movie/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m1AsJson);
		
		Movie savedMovie = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		
		String savedMovieAsJson = this.mapper.writeValueAsString(savedMovie);
		
		ResultMatcher matchStatus = status().isCreated();
		
		ResultMatcher matchBody = content().json(savedMovieAsJson);
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		

	}

}
