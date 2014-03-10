package com.crimealert.data;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.crimealert.enums.Role;
import com.crimealert.model.Complaint;
import com.crimealert.model.Crime;
import com.crimealert.model.Shop;
import com.crimealert.model.User;

@Component
public class DummyDataGenerator {

	public Shop createShop() {
	   final Shop s = new Shop();
	   s.setName("Shop Name");
	   s.setEmplNumber(12);
	   return s;
	}
	
	public Complaint createComplaint() {
		final Complaint c = new Complaint();
		c.setAddress("address");
		c.setCity("city");
		c.setComplaintDate(new Date(new java.util.Date().getTime()));
		c.setCountry("contry");
		c.setDescription("description");
		c.setLocation("location");
		c.setMap("map");
		c.setProvience("province");
		c.setTitle("title");
		return c;
	}
	
	public User createUser() {
		final User u = new User();
		u.setAbout("about");
		u.setAddress("address");
		u.setCity("city");
		u.setCountry("country");
		u.setEmail("email");
		u.setEnabled(true);
		u.setFirstName("fname");
		u.setLastName("lname");
		u.setPassword("123456");
		u.setPhoto("photo");
		u.setProvience("provience");
		u.setRole(Role.USER);
		return u;
	}
	
	public Crime createCrime() {
		final Crime c = new Crime();
		c.setAddress("address");
		//c.setCity("city");
		c.setCrimeDate(new Date(new java.util.Date().getTime()));
		c.setCountry("contry");
		c.setDescription("description");
		c.setLocation("location");
		c.setMap("map");
		//c.setProvience("province");
		c.setTitle("title");
		return c;
	}	
}
