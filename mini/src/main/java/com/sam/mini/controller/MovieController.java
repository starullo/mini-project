package com.sam.mini.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.mini.domain.Movie;
import com.sam.mini.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
//	ATTRIBUTES
	private MovieService service; 
	
//	CONSTRUCTORS
	@Autowired
	public MovieController(MovieService service) {
		this.service = service; 
	}
	
	// CREATE
	@PostMapping("/create")
	public ResponseEntity<Movie> createVehicle(@RequestBody Movie movieToAdd){
		return new ResponseEntity<Movie>(this.service.createMovie(movieToAdd), HttpStatus.CREATED);
	}
	
	// READ
	@GetMapping("/getAll")
	public ResponseEntity<List<Movie>> getAllMovies(){
		return new ResponseEntity(this.service.getAllMovies(), HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Movie> getVehicle(@PathVariable Long id) {
		return new ResponseEntity(this.service.getMovie(id), HttpStatus.OK);
	}
	
//	UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie){
		return new ResponseEntity<Movie>(this.service.updateMovie(id, updatedMovie), HttpStatus.ACCEPTED);
	}
	
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteMovie(@PathVariable Long id) {
		return this.service.deleteMovie(id) == true ? new ResponseEntity<Movie>(HttpStatus.OK) : new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
	}
	

}
