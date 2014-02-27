package com.crimealert.service;

import com.crimealert.model.User;

public interface UserService {
  
  User getUserByEmail(String email);
  User saveUser(final User u);  
  User getUserById(int id);
}
