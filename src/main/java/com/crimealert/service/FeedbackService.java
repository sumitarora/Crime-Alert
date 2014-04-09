package com.crimealert.service;

import java.util.List;

import com.crimealert.model.Feedback;
import com.crimealert.model.User;

public interface FeedbackService {

	Feedback saveFeedback(Feedback feedback);
	List<Feedback> getAllByUser(User user);
	List<Feedback> getAll();
	Feedback getFeedbackById(Long id);
	
	void deleteFeedback(Long id);
}
