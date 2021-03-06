package com.crimealert.service;

import java.util.List;

import com.crimealert.model.User;

public interface UserService {
  
  User getUserByEmail(String email);
  
  User saveUser(final User u, final Boolean create);  
  
  User updateUser(final User u);  
  
  User getUserById(int id);
  
  List<User> getAllUsers();
  
  List<User> searchUsers(String criteria);
  
  User verifyEmail(String token);
  
  User generateForgotPasswordToken(User user);
  
  User findByForgotPasswordToken(String token);
}
