package com.sam.mini.controller;

import java.time.LocalDate;
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

import com.sam.mini.domain.Actor;
import com.sam.mini.service.ActorService;

@RestController
@RequestMapping("/actor")
public class ActorController {
	
//	ATTRIBUTES
	private ActorService service; 
	
//	CONSTRUCTORS
	@Autowired
	public ActorController(ActorService service) {
		this.service = service; 
	}
	
	// CREATE
	@PostMapping("/create")
	public ResponseEntity<Actor> createVehicle(@RequestBody Actor actorToAdd){
		return new ResponseEntity<Actor>(this.service.createActor(actorToAdd), HttpStatus.CREATED);
	}
	
	// READ
	@GetMapping("/getAll")
	public ResponseEntity<List<Actor>> getAllMovies(){
		return new ResponseEntity(this.service.getAllActors(), HttpStatus.OK);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<Actor> getVehicle(@PathVariable Long id) {
		return new ResponseEntity(this.service.getActor(id), HttpStatus.OK);
	}
	
//	UPDATE
	@PutMapping("/update/{id}")
	public ResponseEntity<Actor> updateMovie(@PathVariable Long id, @RequestBody Actor updatedActor){
		return new ResponseEntity<Actor>(this.service.updateActor(id, updatedActor), HttpStatus.ACCEPTED);
	}
	
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteActor(@PathVariable Long id) {
		return this.service.deleteActor(id) == true ? new ResponseEntity<Actor>(HttpStatus.OK) : new ResponseEntity<Actor>(HttpStatus.BAD_REQUEST);
	}
	

}
