package com.crimealert.service;

import java.util.List;

import com.crimealert.model.User;

public interface UserService {
  
  User getUserByEmail(String email);
  User saveUser(final User u);  
  User getUserById(int id);
  
  List<User> getAllUsers();
}
