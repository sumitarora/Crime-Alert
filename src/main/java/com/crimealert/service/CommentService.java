package com.crimealert.service;

import java.util.List;

import com.crimealert.model.Comment;
import com.crimealert.model.User;
public interface CommentService {
	
	Comment saveComment(Comment comment);
	
	List<Comment> getAllUserComments(User user);
	
	List<Comment> findByDescription(String description);

}
