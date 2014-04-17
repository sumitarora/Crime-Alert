package com.crimealert.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crimealert.init.BaseTestCase;
import com.crimealert.model.Crime;
import com.crimealert.model.User;

public class TestCrimeService extends BaseTestCase {

	@Autowired
	UserService userService;

	@Autowired
	CrimeService crimeService;
	
	@Test
	public void testSample() {
		int a = 10;
		int b = 10;
		assertEquals(a+b, 20);
	}
	
	@Test
	public void testSaveCrime() {
		final User user = userService.saveUser(ddg.createUser(), false);
		assertTrue(user.getUserId() > 0);
		
		Crime crime = ddg.createCrime();
		crime.setUser(user);
		crime = crimeService.saveCrime(crime);
		assertTrue(crime.getCrimeId() > 0);		
	}
	
	@Test
	public void testDeleteCrime() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Crime crime = ddg.createCrime();
		crime.setUser(user);
		crime = crimeService.saveCrime(crime);
		assertTrue(crime.getCrimeId() > 0);
		
		crimeService.deleteCrime(crime.getCrimeId());
		crime = crimeService.findCrimeById(crime.getCrimeId());
		assertNull(crime);		
	}
	
	@Test
	public void testFindCrimeById() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Crime crime = ddg.createCrime();
		crime.setUser(user);
		crime = crimeService.saveCrime(crime);
		assertTrue(crime.getCrimeId() > 0);
		
		final Crime fetchedCrime = crimeService.findCrimeById(crime.getCrimeId());
		
		assertEquals(crime.getCrimeId(), fetchedCrime.getCrimeId());
		assertEquals(crime.getAddress(), fetchedCrime.getAddress());
		//assertEquals(crime.getCity(), fetchedCrime.getCity());
		assertEquals(crime.getCountry(), fetchedCrime.getCountry());
		assertEquals(crime.getDescription(), fetchedCrime.getDescription());
		assertEquals(crime.getLocation(), fetchedCrime.getLocation());
		assertEquals(crime.getMap(), fetchedCrime.getMap());
		//assertEquals(crime.getProvience(), fetchedCrime.getProvience());
		assertEquals(crime.getTitle(), fetchedCrime.getTitle());		
	}
	
	@Test
	public void testGetAllCrimes() {
		final User user = userService.saveUser(ddg.createUser());
		assertTrue(user.getUserId() > 0);
		
		Crime crime = ddg.createCrime();
		crime.setUser(user);
		crime = crimeService.saveCrime(crime);
		assertTrue(crime.getCrimeId() > 0);
		
		final List<Crime> crimes = null;//crimeService.getAllCrimes();
		assertTrue(crimes.size() > 0);			
	}
}
