package com.crimealert.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.Crime;
import com.crimealert.model.User;
import com.crimealert.repository.UserRepository;
import com.crimealert.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public User getUserByEmail(String email) {
		List<User> users = userRepository.findByEmail(email);
		if(users.size() != 0) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public User saveUser(User u) {
		u = userRepository.save(u);
		return u;
	}
	
	@Override
	public User getUserById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public List<User> searchUsers(String criteria) {
		String query = "select * from tbl_user u where u.first_name like '%"+criteria+"%' or u.last_name like '%"+criteria+"%' or u.email like '%"+criteria+"%'";
	    final Query q = em.createNativeQuery(query, User.class);
	    return q.getResultList();		
	}
}
