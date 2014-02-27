package com.crimealert.service;

import java.util.List;

//import com.crimealert.exception.CrimeNotFound;
import com.crimealert.model.Crime;
public interface CrimeService {
	
	Crime saveCrime(Crime crime);
	void deleteCrime(int id);
	Crime findCrimeById(int id);	
	List<Crime> getAllCrimes();
	
}
