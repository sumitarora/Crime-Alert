package com.crimealert.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crimealert.model.Complaint;
import com.crimealert.model.User;

public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {
	List<Complaint> findComplaintsByUser(User user);
}

