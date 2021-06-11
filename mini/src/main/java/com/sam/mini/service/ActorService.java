package com.sam.mini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sam.mini.domain.Actor;
import com.sam.mini.repo.ActorRepo;

@Service
public class ActorService {
	
//	ATTRIBUTES
	private ActorRepo repo; 
	
//	CONSTRUCTORS
	public ActorService(ActorRepo repo) {
		this.repo = repo; 
	}
	
//	CREATE
	public Actor createMovie(Actor a) {
		return this.repo.save(a);
	}

//	READ
	public List<Actor> getAllMovies(){
		return this.repo.findAll(); 
	}
	
	public Actor getMovie(Long id) {
		Optional<Actor> m = this.repo.findById(id);
		return m.get(); 
	}
	
//	UPDATE
	public Actor updateMovie(Long id, Actor newActor) {
		Actor toUpdate = this.getMovie(id);
		toUpdate.setName(newActor.getName());
		toUpdate.setDob(newActor.getDob());
		toUpdate.setSamLikes(newActor.isSamLikes());
		
		Actor updated = this.repo.save(toUpdate);
		
		return updated; 
	}
	
//	DELETE
	public boolean deleteMovie(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
