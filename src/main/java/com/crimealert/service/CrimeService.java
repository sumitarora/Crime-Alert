package com.crimealert.service;

import java.util.List;

import com.crimealert.model.Crime;
import com.crimealert.model.User;
public interface CrimeService {
	
	Crime saveCrime(Crime crime);
	void deleteCrime(int id);
	Crime findCrimeById(int id);	
	List<Crime> getAllCrimes(User user);
	List<Crime> getAllCrimes();
	
	List<Crime> findByTitleOrDescription(String title, String description);
	
	List<Crime> findTopCrimes();
}
