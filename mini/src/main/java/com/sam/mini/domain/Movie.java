package com.sam.mini.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
	
//	ATTRIBUTES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private Integer year;
	private Double rating;
	private String summary;
	private boolean samLikes;
	
	
	
//	CONSTRUCTORS
	
	public Movie() {
		
	}
	
	public Movie(String title, Integer year, Double rating, String summary, boolean samLikes) {
		super();
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.summary = summary;
		this.samLikes = samLikes;
	}
	
	
//	GETTERS/SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isSamLikes() {
		return samLikes;
	}

	public void setSamLikes(boolean samLikes) {
		this.samLikes = samLikes;
	}
	
	
	
}
