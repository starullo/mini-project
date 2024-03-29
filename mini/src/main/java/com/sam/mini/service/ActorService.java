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
	public Actor createActor(Actor a) {
		return this.repo.save(a);
	}

//	READ
	public List<Actor> getAllActors(){
		return this.repo.findAll(); 
	}
	
	public Actor getActor(Long id) {
		Optional<Actor> m = this.repo.findById(id);
		return m.get(); 
	}
	
//	UPDATE
	public Actor updateActor(Long id, Actor newActor) {
		Actor a = this.getActor(id);
		a.setName(newActor.getName());
		a.setDob(newActor.getDob());
		a.setSamLikes(newActor.isSamLikes());
		
		Actor updated = this.repo.save(a);
		
		return updated;
	}
	
//	DELETE
	public boolean deleteActor(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}

}
