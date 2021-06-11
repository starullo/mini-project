package com.sam.mini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sam.mini.domain.Movie;
import com.sam.mini.repo.MovieRepo;

@Service
public class MovieService {
	
//	ATTRIBUTES
	private MovieRepo repo; 
	
//	CONSTRUCTORS
	public MovieService(MovieRepo repo) {
		this.repo = repo; 
	}
	
//	CREATE
	public Movie createMovie(Movie m) {
		return this.repo.save(m);
	}

//	READ
	public List<Movie> getAllMovies(){
		return this.repo.findAll(); 
	}
	
	public Movie getMovie(Long id) {
		Optional<Movie> m = this.repo.findById(id);
		return m.get(); 
	}
	
//	UPDATE
	public Movie updateMovie(Long id, Movie newMovie) {
		Movie m = this.getMovie(id);
		
		m.setTitle(newMovie.getTitle());
		m.setRating(newMovie.getRating());
		m.setSummary(newMovie.getSummary());
		m.setYear(newMovie.getYear());
		
		Movie updated = this.repo.save(m);
		return updated;
	}
	
//	DELETE
	public boolean deleteMovie(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
