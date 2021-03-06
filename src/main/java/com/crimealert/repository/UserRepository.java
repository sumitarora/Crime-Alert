package com.crimealert.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByForgotPasswordToken(String token);
    
    List<User> findByEmail(String email);
    
    List<User> findAll();
    
    User findByVerifyToken(String token);
    
}
