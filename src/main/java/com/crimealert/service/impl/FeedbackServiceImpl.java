package com.crimealert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.Feedback;
import com.crimealert.model.User;
import com.crimealert.repository.FeedbackRepository;
import com.crimealert.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	public Feedback saveFeedback(Feedback feedback) {
		return feedbackRepo.save(feedback);
	}

	@Override
	public List<Feedback> getAllByUser(User user) {
		return feedbackRepo.findByUser(user);
	}

	@Override
	public Feedback getFeedbackById(Long id) {
		return feedbackRepo.findOne(id);
	}

	@Override
	public List<Feedback> getAll() {
		return feedbackRepo.findAll();
	}

	@Override
	public void deleteFeedback(Long id) {
		feedbackRepo.delete(id);
	}
}
