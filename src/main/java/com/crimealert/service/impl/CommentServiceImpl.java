package com.crimealert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimealert.model.Comment;
import com.crimealert.model.User;
import com.crimealert.repository.CommentRepository;
import com.crimealert.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public List<Comment> getAllUserComments(User user) {
		return commentRepository.findCommentsByUser(user);
	}

	@Override
	public List<Comment> findByDescription(String description) {
		return null;
	}

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

}
