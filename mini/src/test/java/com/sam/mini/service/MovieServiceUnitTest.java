package com.sam.mini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sam.mini.domain.Movie;
import com.sam.mini.repo.MovieRepo;

import antlr.collections.List;

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
		
		Mockito.when(this.repo.save(m1)).thenReturn(m1WithId);
		
		assertEquals(this.service.createMovie(m1), m1WithId);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(m1);
	}
	
	@Test
	void testRead() {
		Movie m1 = new Movie("Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie m2 = new Movie("Peter Rabbit 2: The Runaway", 2021, 6.7, "Peter Rabbit causes trouble... BIG trouble... well not really I don't know whatever");
		Movie m1WithId = new Movie(1L, "Goodfellas", 1990, 9.9, "Wise guys do unwise things");
		Movie m2WithId = new Movie(2L, "Peter Rabbit 2: The Runaway", 2021, 6.7, "Peter Rabbit causes trouble... BIG trouble... well not really I don't know whatever");
		ArrayList movies = new ArrayList<Movie>();
		movies.add(m1WithId);
		movies.add(m2WithId);
		
		Mockito.when(this.repo.findAll()).thenReturn(movies);
		
		assertEquals(this.service.getAllMovies(), movies);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
}
