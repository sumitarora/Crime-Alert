package com.crimealert.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Feedback;
import com.crimealert.model.User;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    List<Feedback> findAll();
    List<Feedback> findByUser(User user);
    
}
