package com.crimealert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.User;
import com.crimealert.repository.UserRepository;
import com.crimealert.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User loadUserByUsername(String email) {
		List<User> users = userRepository.findByEmail(email);
		if(users.size() != 0) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public User insertUser(User u) {
		
		u = userRepository.save(u);
		return u;
	}

}