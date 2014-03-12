package com.crimealert.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Comment;
import com.crimealert.model.Complaint;
import com.crimealert.model.User;

public interface CommentRepository extends CrudRepository<Comment, Integer>{
	
	List<Comment> findCommentsByUser(User user);
	List<Complaint> findComplaintsByUser(User user);
	
}