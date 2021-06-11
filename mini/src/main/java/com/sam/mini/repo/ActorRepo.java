package com.sam.mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.mini.domain.Movie;

@Repository
public interface ActorRepo extends JpaRepository<Movie,Long>{

}
