package com.crimealert.service;

import java.util.List;

import com.crimealert.model.Complaint;

public interface ComplaintService {

	Complaint saveComplaint(Complaint complaint);
	void deleteComplaint(int id);
	Complaint getComplaintById(int id);
	List<Complaint> getAllComplaints();

}

