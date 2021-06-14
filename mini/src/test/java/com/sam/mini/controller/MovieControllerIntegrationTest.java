package com.sam.mini.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
		
		Movie savedMovie = new Movie(2L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		
		String savedMovieAsJson = this.mapper.writeValueAsString(savedMovie);
		
		ResultMatcher matchStatus = status().isCreated();
		
		ResultMatcher matchBody = content().json(savedMovieAsJson);
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
		

	}
	
	@Test
	void testRead() throws Exception {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		String m1AsJson = this.mapper.writeValueAsString(m1);
		
		RequestBuilder mockRequestCreate = 
				post("/movie/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m1AsJson);
		
		Movie savedM1 = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		String savedM1AsJson = this.mapper.writeValueAsString(savedM1);
		
		RequestBuilder mockRequestOne = get("/movie/getOne/1");
		
		ResultMatcher matchStatus = status().is(200);
		ResultMatcher matchBody = content().json(savedM1AsJson);
		
		this.mock.perform(mockRequestCreate);
		this.mock.perform(mockRequestOne).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void testUpdate() throws Exception {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie updated = new Movie("Goodfellas", 1990, 10.0, "They're good and they're fellas and it's a movie hooray");
		String m1AsJson = this.mapper.writeValueAsString(m1);
		String updatedAsJson = this.mapper.writeValueAsString(updated);
		
		RequestBuilder mockRequest = 
				post("/movie/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m1AsJson);
		Movie savedMovie = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		String savedMovieAsJson = this.mapper.writeValueAsString(savedMovie);
		
		RequestBuilder mockRequest2 = 
				put("/movie/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updatedAsJson);
		Movie savedUpdated = new Movie(1L, "Goodfellas", 1990, 10.0, "They're good and they're fellas and it's a movie hooray");
		String savedUpdatedAsJson = this.mapper.writeValueAsString(savedUpdated);

		ResultMatcher matchStatus = status().is(202);
		ResultMatcher matchBody = content().json(savedUpdatedAsJson);
		this.mock.perform(mockRequest);
		this.mock.perform(mockRequest2).andExpect(matchStatus).andExpect(matchBody);
		
	}
	
	@Test
	void testDelete() throws Exception {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie m2 = new Movie("Peter Rabbit 2: The Runaway", 2021, 6.7, "Peter Rabbit causes trouble... BIG trouble... well not really I don't know whatever");
		String m1AsJson = this.mapper.writeValueAsString(m1);
		String m2AsJson = this.mapper.writeValueAsString(m2);
		
		
		Movie savedOne = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie savedTwo = new Movie(2L, "Peter Rabbit 2: The Runaway", 2021, 6.7, "Peter Rabbit causes trouble... BIG trouble... well not really I don't know whatever");
		String savedOneToJson = this.mapper.writeValueAsString(savedOne);
		String savedTwoToJson = this.mapper.writeValueAsString(savedTwo);
		Movie [] results = new Movie[1];
		results[0] = savedTwo;
		String resultsToJson = this.mapper.writeValueAsString(results);
		
		RequestBuilder mockRequest1 = 
				post("/movie/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m1AsJson);
		
		RequestBuilder mockRequest2 = 
				post("/movie/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m2AsJson);
		
		RequestBuilder mockDelete = delete("/movie/delete/1");
		
		RequestBuilder mockRequest3 = get("/movie/getAll");
		
		ResultMatcher matchStatus = status().is(202);
		ResultMatcher matchBody = content().json(resultsToJson);
		
		this.mock.perform(mockRequest1);
		this.mock.perform(mockRequest2);
		this.mock.perform(mockRequest3).andExpect(matchBody);
		
		
	}

}
