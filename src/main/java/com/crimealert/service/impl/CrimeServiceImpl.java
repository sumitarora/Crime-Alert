package com.crimealert.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimealert.model.Crime;
import com.crimealert.repository.CrimeRepository;
import com.crimealert.service.CrimeService;

@Service
public class CrimeServiceImpl implements CrimeService {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	private CrimeRepository crimeRepository;

	@Override
	@Transactional
	public Crime saveCrime(Crime crime) {
		return crimeRepository.save(crime);
	}

	@Override
	public void deleteCrime(int id) {
		crimeRepository.delete(id);		
	}

	@Override
	public Crime findCrimeById(int id) {
		return crimeRepository.findOne(id);
	}

	@Override
	public List<Crime> getAllCrimes() {
		return (List<Crime>) crimeRepository.findAll();
	}
	
	@Override
	public List<Crime> findByTitleOrDescription(String title, String description) {
		String query = "select * from tbl_crime where title like '%"+title+"%' or description like '%"+description+"%'";
	    final Query q = em.createNativeQuery(query, Crime.class);
	    return q.getResultList();		
	}

}
