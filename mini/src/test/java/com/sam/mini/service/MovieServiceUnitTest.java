package com.sam.mini.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sam.mini.domain.Movie;
import com.sam.mini.repo.MovieRepo;

@SpringBootTest
public class MovieServiceUnitTest {

	@MockBean
	private MovieRepo repo;
	
	@Autowired
	private MovieService service;
	
	@Test
	void testCreateUnit() {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie m1WithId = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
	}
	
}
