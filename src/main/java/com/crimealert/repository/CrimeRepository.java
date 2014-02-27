package com.crimealert.repository;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Crime;

public interface CrimeRepository extends CrudRepository<Crime, Integer>{
	
}