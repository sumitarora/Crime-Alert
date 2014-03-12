package com.crimealert.service;

import java.util.List;

import com.crimealert.model.Complaint;
import com.crimealert.model.User;

public interface ComplaintService {

	Complaint saveComplaint(Complaint complaint);
	void deleteComplaint(int id);
	Complaint getComplaintById(int id);
	List<Complaint> getAllComplaints(User user);
	List<Complaint> getAllComplaints();

	List<Complaint> findByTitleOrDescription(String title, String description);
}

