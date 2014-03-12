package com.crimealert.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Crime;
import com.crimealert.model.User;

public interface CrimeRepository extends CrudRepository<Crime, Integer>{
	
	List<Crime> findCrimeByUser(User user);
}