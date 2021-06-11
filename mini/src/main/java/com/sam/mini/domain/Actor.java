package com.sam.mini.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {
	
//	ATTRIBUTES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private LocalDate dob;
	private boolean samLikes;
	
	
	
//	CONSTRUCTORS
	
	public Actor() {
		
	}
	
	public Actor(String name, LocalDate dob, boolean samLikes) {
		super();
		this.name = name;
		this.dob = dob;
		this.samLikes = samLikes;
	}

//	GETTERS/SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public boolean isSamLikes() {
		return samLikes;
	}

	public void setSamLikes(boolean samLikes) {
		this.samLikes = samLikes;
	}
	
	
	
}
