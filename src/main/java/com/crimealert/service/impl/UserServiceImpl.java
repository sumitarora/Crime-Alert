package com.crimealert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.enums.Role;
import com.crimealert.model.User;
import com.crimealert.repository.UserRepository;
import com.crimealert.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) {
		List<User> users = userRepository.findByUsername(username);
		if(users.size() != 0) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public User insertUser(String username, String password) {
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setEnabled(true);
		u.setRole(Role.ROLE_USER);
		u = userRepository.save(u);
		return u;
	}

}
