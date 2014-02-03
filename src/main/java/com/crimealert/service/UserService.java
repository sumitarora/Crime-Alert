package com.crimealert.service;

import com.crimealert.model.User;

public interface UserService {
  
  public User loadUserByUsername(String username);

  public User insertUser(String username, String password);
}
